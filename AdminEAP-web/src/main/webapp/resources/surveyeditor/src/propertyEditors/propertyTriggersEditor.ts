import * as ko from "knockout";
import * as Survey from "survey-knockout";
import { SurveyPropertyItemsEditor } from "./propertyItemsEditor";
import { SurveyPropertyEditorBase } from "./propertyEditorBase";
import { editorLocalization } from "../editorLocalization";
import { SurveyPropertyEditorFactory } from "./propertyEditorFactory";

export class SurveyPropertyTriggersEditor extends SurveyPropertyItemsEditor {
  koElements: any;
  koQuestions: any;
  koPages: any;
  koQuestionNames: any;
  public koSelected: any;
  public koTriggers: any;
  public availableTriggers: Array<string> = [];
  private triggerClasses: Array<Survey.JsonMetadataClass> = [];
  constructor(property: Survey.JsonObjectProperty) {
    super(property);
    var self = this;
    this.onDeleteClick = function() {
      self.koItems.remove(self.koSelected());
    };
    this.onAddClick = function(item) {
      self.addItem(item.value);
    };
    this.koSelected = ko.observable(null);
    this.koPages = ko.observableArray();
    this.koQuestions = ko.observableArray();
    this.koQuestionNames = ko.observableArray();
    this.koElements = ko.observableArray();
    this.triggerClasses = Survey.JsonObject.metaData.getChildrenClasses(
      "surveytrigger",
      true
    );
    this.availableTriggers = this.getAvailableTriggers();
    this.koTriggers = ko.observableArray(this.getLocalizedTriggers());
  }
  public get editorType(): string {
    return "triggers";
  }
  protected onValueChanged() {
    if (this.editingObject) {
      var allQuestions = (<Survey.Survey>this.editingObject).getAllQuestions();
      this.koPages(this.getNames((<Survey.Survey>this.editingObject).pages));
      this.koQuestions(this.getNames(allQuestions));
      this.koQuestionNames(this.getQuestionNames(allQuestions));
      this.koElements(this.getNames(this.getAllElements()));
    }
    super.onValueChanged();
    if (this.koSelected) {
      this.koSelected(this.koItems().length > 0 ? this.koItems()[0] : null);
    }
  }
  //TODO this code should be in the library
  private getAllElements(): Array<any> {
    var res = [];
    var pages = (<Survey.Survey>this.editingObject).pages;
    for (var i = 0; i < pages.length; i++) {
      this.addElemenetsIntoList(pages[i], res);
    }
    return res;
  }
  private addElemenetsIntoList(element: any, list: Array<any>) {
    var elements = element.getElementsInDesign(false);
    if (!elements) return;
    for (var i = 0; i < elements.length; i++) {
      list.push(elements[i]);
      this.addElemenetsIntoList(<Survey.SurveyElement>elements[i], list);
    }
  }
  private addItem(triggerType: string) {
    var trigger = Survey.JsonObject.metaData.createClass(triggerType);
    var triggerItem = this.createPropertyTrigger(trigger);
    this.koItems.push(triggerItem);
    this.koSelected(triggerItem);
  }
  protected createEditorItem(item: any) {
    var jsonObj = new Survey.JsonObject();
    var trigger = Survey.JsonObject.metaData.createClass(item.getType());
    jsonObj.toObject(item, trigger);
    return this.createPropertyTrigger(<Survey.SurveyTrigger>trigger);
  }
  protected createItemFromEditorItem(editorItem: any) {
    var editorTrigger = <SurveyPropertyTrigger>editorItem;
    return editorTrigger.createTrigger();
  }
  private getLocalizedTriggers(): Array<any> {
    var res = [];
    for (var i = 0; i < this.availableTriggers.length; i++) {
      var name = this.availableTriggers[i];
      res.push({ value: name, text: editorLocalization.getTriggerName(name) });
    }
    return res;
  }
  private getAvailableTriggers(): Array<string> {
    var result = [];
    for (var i = 0; i < this.triggerClasses.length; i++) {
      result.push(this.triggerClasses[i].name);
    }
    return result;
  }
  private getNames(items: Array<any>): Array<string> {
    var names = [];
    for (var i = 0; i < items.length; i++) {
      var item = items[i];
      if (item["name"]) {
        names.push(item["name"]);
      }
    }
    return names;
  }
  private getQuestionNames(questions: Array<Survey.IQuestion>): Array<string> {
    var items = [];
    for (var i = 0; i < questions.length; i++) {
      (<Survey.QuestionBase>questions[i]).addConditionNames(items);
    }
    var names = [];
    for (var i = 0; i < items.length; i++) {
      if (items[i].indexOf("[") < 0) {
        names.push(items[i]);
      }
    }
    return names;
  }
  private createPropertyTrigger(
    trigger: Survey.SurveyTrigger
  ): SurveyPropertyTrigger {
    var triggerItem = null;
    if (trigger.getType() == "visibletrigger") {
      triggerItem = new SurveyPropertyVisibleTrigger(
        <Survey.SurveyTriggerVisible>trigger,
        this.koPages,
        this.koElements
      );
    }
    if (trigger.getType() == "setvaluetrigger") {
      triggerItem = new SurveyPropertySetValueTrigger(
        <Survey.SurveyTriggerSetValue>trigger,
        this.koQuestions
      );
    }
    if (!triggerItem) {
      triggerItem = new SurveyPropertyTrigger(trigger);
    }
    return triggerItem;
  }
}
export class SurveyPropertyTrigger {
  private triggerType: string;
  availableOperators = [];
  koName: any;
  koOperator: any;
  koValue: any;
  koType: any;
  koText: any;
  koIsValid: any;
  koRequireValue: any;

  constructor(public trigger: Survey.SurveyTrigger) {
    this.availableOperators = SurveyPropertyEditorFactory.getOperators();
    this.triggerType = trigger.getType();
    this.koType = ko.observable(this.triggerType);
    this.koName = ko.observable(trigger.name);
    this.koOperator = ko.observable(trigger.operator);
    this.koValue = ko.observable(trigger.value);
    var self = this;
    this.koRequireValue = ko.computed(() => {
      return self.koOperator() != "empty" && self.koOperator() != "notempty";
    });
    this.koIsValid = ko.computed(() => {
      if (self.koName() && (!self.koRequireValue() || self.koValue()))
        return true;
      return false;
    });
    this.koText = ko.computed(() => {
      self.koName();
      self.koOperator();
      self.koValue();
      return self.getText();
    });
  }
  public createTrigger(): Survey.SurveyTrigger {
    var trigger = <Survey.SurveyTrigger>Survey.JsonObject.metaData.createClass(
      this.triggerType
    );
    trigger.name = this.koName();
    trigger.operator = this.koOperator();
    trigger.value = this.koValue();
    return trigger;
  }
  private getText(): string {
    if (!this.koIsValid())
      return editorLocalization.getString("pe.triggerNotSet");
    return (
      editorLocalization.getString("pe.triggerRunIf") +
      " '" +
      this.koName() +
      "' " +
      this.getOperatorText() +
      this.getValueText()
    );
  }
  private getOperatorText(): string {
    var op = this.koOperator();
    for (var i = 0; i < this.availableOperators.length; i++) {
      if (this.availableOperators[i].name == op)
        return this.availableOperators[i].text;
    }
    return op;
  }
  private getValueText(): string {
    if (!this.koRequireValue()) return "";
    return " " + this.koValue();
  }
}

export class SurveyPropertyVisibleTrigger extends SurveyPropertyTrigger {
  public pages: SurveyPropertyTriggerObjects;
  public questions: SurveyPropertyTriggerObjects;
  constructor(
    public trigger: Survey.SurveyTriggerVisible,
    koPages: any,
    koQuestions: any
  ) {
    super(trigger);
    this.pages = new SurveyPropertyTriggerObjects(
      editorLocalization.getString("pe.triggerMakePagesVisible"),
      koPages(),
      trigger.pages
    );
    this.questions = new SurveyPropertyTriggerObjects(
      editorLocalization.getString("pe.triggerMakeQuestionsVisible"),
      koQuestions(),
      trigger.questions
    );
  }
  public createTrigger(): Survey.SurveyTrigger {
    var trigger = <Survey.SurveyTriggerVisible>super.createTrigger();
    trigger.pages = this.pages.koChoosen();
    trigger.questions = this.questions.koChoosen();
    return trigger;
  }
}

export class SurveyPropertySetValueTrigger extends SurveyPropertyTrigger {
  koQuestions: any;
  kosetToName: any;
  kosetValue: any;
  koisVariable: any;
  constructor(public trigger: Survey.SurveyTriggerSetValue, koQuestions: any) {
    super(trigger);
    this.koQuestions = koQuestions;
    this.kosetToName = ko.observable(trigger.setToName);
    this.kosetValue = ko.observable(trigger.setValue);
    this.koisVariable = ko.observable(trigger.isVariable);
  }
  public createTrigger(): Survey.SurveyTrigger {
    var trigger = <Survey.SurveyTriggerSetValue>super.createTrigger();
    trigger.setToName = this.kosetToName();
    trigger.setValue = this.kosetValue();
    trigger.isVariable = this.koisVariable();
    return trigger;
  }
}
export class SurveyPropertyTriggerObjects {
  koObjects: any;
  koChoosen: any;
  koSelected: any;
  koChoosenSelected: any;
  public onDeleteClick: any;
  public onAddClick: any;
  constructor(
    public title: string,
    allObjects: Array<string>,
    choosenObjects: Array<string>
  ) {
    this.koChoosen = ko.observableArray(choosenObjects);
    var array = [];
    for (var i = 0; i < allObjects.length; i++) {
      var item = allObjects[i];
      if (choosenObjects.indexOf(item) < 0) {
        array.push(item);
      }
    }
    this.koObjects = ko.observableArray(array);
    this.koSelected = ko.observable();
    this.koChoosenSelected = ko.observable();
    var self = this;
    this.onDeleteClick = function() {
      self.deleteItem();
    };
    this.onAddClick = function() {
      self.addItem();
    };
  }
  private deleteItem() {
    this.changeItems(this.koChoosenSelected(), this.koChoosen, this.koObjects);
  }
  private addItem() {
    this.changeItems(this.koSelected(), this.koObjects, this.koChoosen);
  }
  private changeItems(item: string, removedFrom: any, addTo: any) {
    removedFrom.remove(item);
    addTo.push(item);
    removedFrom.sort();
    addTo.sort();
  }
}

SurveyPropertyEditorFactory.registerEditor("triggers", function(
  property: Survey.JsonObjectProperty
): SurveyPropertyEditorBase {
  return new SurveyPropertyTriggersEditor(property);
});
