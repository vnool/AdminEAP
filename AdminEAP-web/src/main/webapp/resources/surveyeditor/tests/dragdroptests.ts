import * as ko from "knockout";
import * as Survey from "survey-knockout";
import { DragDropTargetElement, DragDropHelper } from "../src/dragdrophelper";

export default QUnit.module("Drag and Drop Tests");

QUnit.test("Show/hide new created item, simple test", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("p1");
  var q1 = page.addNewQuestion("text", "q1");
  var q2 = page.addNewQuestion("text", "q2");
  var target = new Survey.QuestionText("qt");
  var dragTarget = new DragDropTargetElement(page, target, null);
  assert.equal(page["koRows"]().length, 2, "Two elements initially");
  assert.equal(dragTarget.moveTo(q1, false), true, "It can be done: 1");
  assert.equal(page["koRows"]().length, 3, "Move 1");
  assert.equal(page["koRows"]()[0]["koElements"]()[0].name, "qt", "Move 1");
  assert.equal(dragTarget.moveTo(q1, true), true, "It can be done: 2");
  assert.equal(page["koRows"]().length, 3, "Move 2");
  assert.equal(page["koRows"]()[1]["koElements"]()[0].name, "qt", "Move 2");
  assert.equal(dragTarget.moveTo(q2, false), true, "It can be done: 3");
  assert.equal(page["koRows"]().length, 3, "Move 3");
  assert.equal(page["koRows"]()[1]["koElements"]()[0].name, "qt", "Move 3");
  assert.equal(dragTarget.moveTo(q2, true), true, "It can be done: 4");
  assert.equal(page["koRows"]().length, 3, "Move 4");
  assert.equal(page["koRows"]()[2]["koElements"]()[0].name, "qt", "Move 4");
  dragTarget.clear();
  assert.equal(page["koRows"]().length, 2, "clear destination");
  dragTarget.doDrop();
  assert.equal(page.questions.length, 2, "still 2 questions");
  dragTarget.moveTo(q2, false);
  dragTarget.doDrop();
  assert.equal(page.questions.length, 3, "3 questions now");
  assert.equal(page.questions[1].name, "qt", "It is a second question");
});

QUnit.test("Show/hide move item, simple test", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("p1");
  var q1 = page.addNewQuestion("text", "q1");
  var q2 = page.addNewQuestion("text", "q2");
  var target = new Survey.QuestionText("q2");

  var dragTarget = new DragDropTargetElement(page, target, q2);
  assert.equal(page["koRows"]().length, 2, "Two elements initially");
  assert.equal(dragTarget.moveTo(q1, false), true, "It can be done: 1");
  assert.equal(page["koRows"]().length, 3, "Move 1");
  assert.equal(page["koRows"]()[0]["koElements"]()[0].name, "q2", "Move 1");
  dragTarget.clear();
  assert.equal(page["koRows"]().length, 2, "out of page");
  assert.equal(dragTarget.moveTo(q1, true), false, "It can't be done: 2");
  assert.equal(page["koRows"]().length, 2, "Move 2");
  assert.equal(dragTarget.moveTo(q2, false), false, "It can't be done: 3");
  assert.equal(page["koRows"]().length, 2, "Move 3");
  assert.equal(dragTarget.moveTo(q2, true), false, "It can't be done: 4");
  assert.equal(page["koRows"]().length, 2, "Move 4");
  dragTarget.moveTo(q1, false);
  dragTarget.doDrop();
  assert.equal(page.questions.length, 2, "we have only two questions");
  assert.equal(
    page.questions[0].name,
    "q2",
    "The second question becomes the first"
  );
});

QUnit.test("Move item to the end", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("p1");
  var q1 = page.addNewQuestion("text", "q1");
  var q2 = page.addNewQuestion("text", "q2");
  var q3 = page.addNewQuestion("text", "q3");
  var target = new Survey.QuestionText("q1");

  var dragTarget = new DragDropTargetElement(page, target, q1);
  dragTarget.moveTo(q3, true);
  dragTarget.doDrop();
  assert.equal(page.questions.length, 3, "we have only two questions");
  assert.equal(page.questions[2].name, "q1", "The last question is q1 now");
});

QUnit.test("Show/hide/create for empty page", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("p1");
  var target = new Survey.QuestionText("qt");
  var dragTarget = new DragDropTargetElement(page, target, null);
  assert.equal(page["koRows"]().length, 0, "There are no elements initially");
  assert.equal(dragTarget.moveTo(page, false), true, "It can be done: 1");
  assert.equal(page["koRows"]().length, 1, "Move 1");
  assert.equal(page["koRows"]()[0]["koElements"]()[0].name, "qt", "Move 1");
  dragTarget.clear();
  assert.equal(page["koRows"]().length, 0, "clear destination");
  dragTarget.moveTo(page, false);
  dragTarget.doDrop();
  assert.equal(page.questions.length, 1, "one question now");
  assert.equal(page.questions[0].name, "qt", "A new question");
});

QUnit.test("Replace target element", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("p1");
  var ddhelper = new DragDropHelper(survey, null);
  var target = new Survey.QuestionText("qt");

  var result = ddhelper.replaceTargetElement(page);
  assert.equal(result, page);

  var result = ddhelper.replaceTargetElement(target);
  assert.equal(result, target);

  page.addElement(target);
  var result = ddhelper.replaceTargetElement(page);
  assert.equal(result, target);

  var target1 = new Survey.QuestionBoolean("qb");
  page.addElement(target1);
  var result = ddhelper.replaceTargetElement(page);
  assert.equal(result, target1);
});

QUnit.test("Move item startWithNewLine=false", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("p1");
  var q1 = page.addNewQuestion("text", "q1");
  var q2 = page.addNewQuestion("text", "q2");
  var q3 = page.addNewQuestion("text", "q3");
  q1.startWithNewLine = false;
  var target = new Survey.QuestionText("q1");
  target.startWithNewLine = false;

  var dragTarget = new DragDropTargetElement(page, target, q1);
  dragTarget.moveTo(q3, true);
  assert.equal(page["koRows"]().length, 3, "Move 1. No rows should be added");
  assert.equal(
    page["koRows"]()[2]["koElements"]().length,
    2,
    "Move 1. There are two elements in the last row"
  );
  assert.equal(
    page["koRows"]()[2]["koElements"]()[1].name,
    "q1",
    "Move 1. The first question is the last element"
  );
  dragTarget.moveTo(q3, false);
  assert.equal(page["koRows"]().length, 3, "Move 2. No rows should be added");
  assert.equal(
    page["koRows"]()[1]["koElements"]().length,
    2,
    "Move 2. There are two elements in the second row"
  );
  assert.equal(
    page["koRows"]()[2]["koElements"]().length,
    1,
    "Move 2. There is one elements in the last row"
  );
  assert.equal(
    page["koRows"]()[1]["koElements"]()[1].name,
    "q1",
    "Move 2. The first question is the last element in the first row"
  );
  dragTarget.doDrop();
  assert.equal(page.questions.length, 3, "we have only two questions");
  assert.equal(page.questions[1].name, "q1", "The second question is q1 now");
});

QUnit.test("Move item startWithNewLine=false, 2", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("p1");
  var q1 = page.addNewQuestion("text", "q1");
  var q2 = page.addNewQuestion("text", "q2");
  var q3 = page.addNewQuestion("text", "q3");
  q2.startWithNewLine = false;
  var target = new Survey.QuestionText("q2");
  target.startWithNewLine = false;

  var dragTarget = new DragDropTargetElement(page, target, q2);
  dragTarget.moveTo(q3, true);
  dragTarget.doDrop();
  assert.equal(page.questions.length, 3, "we have 3 questions");
  assert.equal(page.questions[2].name, "q2", "The last question is q2 now");
});
QUnit.test("Move item startWithNewLine=false, 3", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("p1");
  var q1 = page.addNewQuestion("text", "q1");
  var q2 = page.addNewQuestion("text", "q2");
  q2.startWithNewLine = false;
  var target = new Survey.QuestionText("q2");
  target.startWithNewLine = false;

  var dragTarget = new DragDropTargetElement(page, target, q2);
  assert.equal(
    dragTarget.moveTo(q1, false),
    true,
    "You can move a question here"
  );
  dragTarget.doDrop();
  assert.equal(page.questions[0].name, "q2", "The first question is q2 now");
});
QUnit.test("Move panel startWithNewLine=false", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("p1");
  var p1 = page.addNewPanel("p1");
  var p2 = page.addNewPanel("p2");
  var p3 = page.addNewPanel("p3");
  p1.startWithNewLine = false;
  var target = new Survey.QuestionText("p1");
  target.startWithNewLine = false;

  var dragTarget = new DragDropTargetElement(page, target, p1);
  dragTarget.moveTo(p3, true, true);
  assert.equal(page["koRows"]().length, 3, "Move 1. No rows should be added");
  assert.equal(
    page["koRows"]()[2]["koElements"]().length,
    2,
    "Move 1. There are two elements in the last row"
  );
  assert.equal(
    page["koRows"]()[2]["koElements"]()[1].name,
    "p1",
    "Move 1. The first panel is the last element"
  );
  dragTarget.moveTo(target, false, false);
  dragTarget.moveTo(p3, true, true);
  dragTarget.moveTo(target, true, true);
  dragTarget.moveTo(p3, true, true);
  assert.equal(page["koRows"]().length, 3, "Move 1.1. No rows should be added");
  assert.equal(
    page["koRows"]()[2]["koElements"]().length,
    2,
    "Move 1.1. There are two elements in the last row"
  );
  assert.equal(
    page["koRows"]()[2]["koElements"]()[1].name,
    "p1",
    "Move 1.1. The first panel is the last element"
  );
  dragTarget.moveTo(p3, false, true);
  assert.equal(page["koRows"]().length, 3, "Move 2. No rows should be added");
  assert.equal(
    page["koRows"]()[1]["koElements"]().length,
    2,
    "Move 2. There are two elements in the second row"
  );
  assert.equal(
    page["koRows"]()[2]["koElements"]().length,
    1,
    "Move 2. There is one elements in the last row"
  );
  assert.equal(
    page["koRows"]()[1]["koElements"]()[1].name,
    "p1",
    "Move 2. The first panel is the last element in the first row"
  );
  dragTarget.doDrop();
  assert.equal(page.elements.length, 3, "we have only 3 panels");
  assert.equal(page.elements[1].name, "p1", "The second element is p1 now");
});

QUnit.test("Move question into empty panel", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("page1");
  var panel = page.addNewPanel("panel1");
  var target = new Survey.QuestionText("q1");

  var dragTarget = new DragDropTargetElement(page, target, null);

  assert.equal(panel["koRows"]().length, 0, "There is no rows");
  assert.equal(
    dragTarget.moveTo(panel, false),
    true,
    "You can move a question here"
  );
  assert.equal(panel["koRows"]().length, 1, "There is one row");
  dragTarget.doDrop();
  assert.equal(panel.questions.length, 1, "There is one question in the panel");
  assert.equal(panel.questions[0].name, "q1", "It is the 'q1' question");
});

QUnit.test("Move panel into empty panel", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("page1");
  var panel = page.addNewPanel("panel1");
  var target = new Survey.Panel("p1");

  var dragTarget = new DragDropTargetElement(page, target, null);

  assert.equal(panel["koRows"]().length, 0, "There is no rows");
  assert.equal(
    dragTarget.moveTo(panel, false),
    true,
    "You can move a question here"
  );
  assert.equal(panel["koRows"]().length, 1, "There is one row");
  dragTarget.doDrop();
  assert.equal(panel.elements.length, 1, "There is one panel in the panel");
  assert.equal(panel.elements[0].name, "p1", "It is the 'p1' panel");
});

QUnit.test("Optionally forbiden move panel into panel", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("page1");
  var panel = page.addNewPanel("panel1");
  var target = new Survey.Panel("p1");

  var dragTarget = new DragDropTargetElement(page, target, null);
  dragTarget.nestedPanelDepth = 0;

  assert.equal(page["koRows"]().length, 1, "There is one row");
  assert.equal(
    dragTarget.moveTo(panel, false),
    true,
    "You can move a question here"
  );
  assert.equal(panel["koRows"]().length, 0, "There is no row in panel");
  assert.equal(page["koRows"]().length, 2, "There are two rows in page");
  dragTarget.doDrop();
  assert.equal(panel.elements.length, 0, "There is no elements in panel");
  assert.equal(page.elements.length, 2, "There are two elements in page");
  assert.equal(page.elements[0].name, "p1", "It is the 'p1' panel");
});

QUnit.test("Move item in panel", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("page1");
  var panel = page.addNewPanel("panel1");
  var q1 = panel.addNewQuestion("text", "q1");
  var q2 = panel.addNewQuestion("text", "q2");
  var target = new Survey.QuestionText("q1");

  var dragTarget = new DragDropTargetElement(page, target, q1);

  assert.equal(panel["koRows"]().length, 2, "There is two rows");
  assert.equal(
    dragTarget.moveTo(q2, true),
    true,
    "You can move a question here"
  );
  assert.equal(panel["koRows"]().length, 3, "There are 3 rows");
  assert.equal(
    panel["koRows"]()[2]["koElements"]()[0].name,
    "q1",
    "Move 1. The 'q1' is in the last row"
  );

  dragTarget.doDrop();
  assert.equal(
    panel.elements.length,
    2,
    "There are two questions in the panel"
  );
  assert.equal(panel.elements[1].name, "q1", "It is the 'q1' question");
});

QUnit.test("Move new item in panel with one question", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("page1");
  var panel = page.addNewPanel("panel1");
  var q1 = panel.addNewQuestion("text", "q1");
  var target = new Survey.QuestionText("q2");

  var dragTarget = new DragDropTargetElement(page, target, null);

  assert.equal(panel["koRows"]().length, 1, "There is two rows");
  assert.equal(
    dragTarget.moveTo(panel, false, false),
    true,
    "Move 0. You can move a question before q1 here"
  );
  assert.equal(panel["koRows"]().length, 2, "Move 0. There are 2 rows");
  assert.equal(
    panel["koRows"]()[0]["koElements"]()[0].name,
    "q2",
    "Move 0. The 'q1' is in the last row"
  );
  assert.equal(
    dragTarget.moveTo(q1, false, true),
    true,
    "Move 1. You can move a question before q1 here"
  );
  assert.equal(panel["koRows"]().length, 2, "Move 1. There are 2 rows");
  assert.equal(
    panel["koRows"]()[0]["koElements"]()[0].name,
    "q2",
    "Move 1. The 'q1' is in the last row"
  );
  assert.equal(
    dragTarget.moveTo(q1, true, true),
    true,
    "Move 2. You can move a question after q1 here"
  );
  assert.equal(panel["koRows"]().length, 2, "Move 1. There are 2 rows");
  assert.equal(
    panel["koRows"]()[1]["koElements"]()[0].name,
    "q2",
    "Move 1. The 'q1' is in the last row"
  );

  dragTarget.doDrop();
  assert.equal(
    panel.elements.length,
    2,
    "There are two questions in the panel"
  );
  assert.equal(panel.elements[1].name, "q2", "It is the 'q2' question");
});

QUnit.test("Move item before panel", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("page1");
  var panel = page.addNewPanel("panel1");
  var target = new Survey.QuestionText("q1");

  var dragTarget = new DragDropTargetElement(page, target, null);

  assert.equal(panel["koRows"]().length, 0, "There is no rows");
  assert.equal(
    dragTarget.moveTo(panel, false, true),
    true,
    "You can move a question here"
  );
  assert.equal(panel["koRows"]().length, 0, "There is no rows in panel");
  assert.equal(page["koRows"]().length, 2, "There are two rows in the page");
  dragTarget.doDrop();
  assert.equal(page.elements.length, 2, "There are two elements on the page");
  assert.equal(page.elements[0].name, "q1", "The first element is 'q1'");
});

QUnit.test("Move item after panel", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("page1");
  var panel = page.addNewPanel("panel1");
  var target = new Survey.QuestionText("q1");

  var dragTarget = new DragDropTargetElement(page, target, null);

  assert.equal(panel["koRows"]().length, 0, "There is no rows");
  assert.equal(
    dragTarget.moveTo(panel, true, true),
    true,
    "You can move a question here"
  );
  assert.equal(panel["koRows"]().length, 0, "There is no rows in panel");
  assert.equal(page["koRows"]().length, 2, "There are two rows in the page");
  dragTarget.doDrop();
  assert.equal(page.elements.length, 2, "There are two elements on the page");
  assert.equal(page.elements[1].name, "q1", "The first element is 'q1'");
});

QUnit.test("Move panel", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("page1");
  var q1 = page.addNewQuestion("text", "q1");
  var q2 = page.addNewQuestion("text", "q1");
  var target = new Survey.Panel("panel1");

  var dragTarget = new DragDropTargetElement(page, target, null);

  assert.equal(page["koRows"]().length, 2, "There is no rows");

  assert.equal(dragTarget.moveTo(q1, false), true, "You can move a panel here");
  assert.equal(page["koRows"]().length, 3, "Move 1. There are 3 rows");
  assert.equal(
    page["koRows"]()[0]["koElements"]()[0].name,
    "panel1",
    "Move 1. The panel is in the first row"
  );

  assert.equal(dragTarget.moveTo(q1, true), true, "You can move a panel here");
  assert.equal(page["koRows"]().length, 3, "Move 2. There are 3 rows");
  assert.equal(
    page["koRows"]()[1]["koElements"]()[0].name,
    "panel1",
    "Move 2. The panel is in the first row"
  );

  assert.equal(dragTarget.moveTo(q2, true), true, "You can move a panel here");
  assert.equal(page["koRows"]().length, 3, "Move 3. There are 3 rows");
  assert.equal(
    page["koRows"]()[2]["koElements"]()[0].name,
    "panel1",
    "Move 3. The panel is in the first row"
  );

  assert.equal(
    dragTarget.moveTo(target, true),
    false,
    "You can't move to itself"
  );

  dragTarget.moveTo(q1, false);
  dragTarget.doDrop();
  assert.equal(page.elements.length, 3, "There are 3 elements on the page");
  assert.equal(
    page.elements[0].name,
    "panel1",
    "The first element is 'panel1'"
  );
});

QUnit.test("Move panel, 2", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("page1");
  var q1 = page.addNewQuestion("text", "q1");
  var target = new Survey.Panel("panel1");

  var dragTarget = new DragDropTargetElement(page, target, null);

  assert.equal(page["koRows"]().length, 1, "There is no rows");

  assert.equal(dragTarget.moveTo(q1, true), true, "You can move a panel here");
  assert.equal(page["koRows"]().length, 2, "Move 1. There are 2 rows");
  assert.equal(
    page["koRows"]()[0]["koElements"]()[0].name,
    "q1",
    "Move 1. The q1 is in the first row"
  );
  assert.equal(
    page["koRows"]()[1]["koElements"]()[0].name,
    "panel1",
    "Move 1. The panel1 is in the last row"
  );

  assert.equal(
    dragTarget.moveTo(q1, true),
    true,
    "Move 2. You can move a panel here"
  );
  assert.equal(page["koRows"]().length, 2, "Move 2. There are 2 rows");
  assert.equal(
    page["koRows"]()[0]["koElements"]()[0].name,
    "q1",
    "Move 2. The q1 is in the first row"
  );
  assert.equal(
    page["koRows"]()[1]["koElements"]()[0].name,
    "panel1",
    "Move 2. The panel1 is in the last row"
  );
});

QUnit.test("Move existing panel, 1", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("page1");
  var q1 = page.addNewQuestion("text", "q1");
  var panel = page.addNewPanel("panel1");
  var q2 = panel.addNewQuestion("text", "q2");
  var target = new Survey.Panel("panel1");
  var targetQ2 = target.addNewQuestion("text", "q2");

  var dragTarget = new DragDropTargetElement(page, target, panel);

  assert.equal(page["koRows"]().length, 2, "There are 2 rows");
  assert.equal(
    dragTarget.moveTo(q1, true),
    false,
    "You can't move a panel here"
  );
  assert.equal(page["koRows"]().length, 2, "Move 1. There are 2 rows");
  assert.equal(dragTarget.moveTo(q1, false), true, "You can move a panel here");
  assert.equal(page["koRows"]().length, 3, "Move 2. There are 3 rows");
  assert.equal(
    page["koRows"]()[0]["koElements"]()[0].name,
    "panel1",
    "Move 2. The panel1 is in the first row"
  );
  assert.equal(
    page["koRows"]()[1]["koElements"]()[0].name,
    "q1",
    "Move 2. The q1 is in the second row"
  );
  dragTarget.doDrop();
  assert.equal(page.elements.length, 2, "There are 2 elements on the page");
  assert.equal(page.elements[0].name, "panel1", "The first element is panel1");
  assert.equal(page.elements[1].name, "q1", "The second element is q1");
  var p = <Survey.Panel>page.elements[0];
  assert.equal(p.elements.length, 1, "There is one element in the panel");
  assert.equal(
    p.elements[0].name,
    "q2",
    "The question in the panel is correct"
  );
});

QUnit.test("Move question out of the panel", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("page1");
  var panel = page.addNewPanel("panel1");
  var q1 = panel.addNewQuestion("text", "q1");

  var target = new Survey.QuestionText("q1");

  var dragTarget = new DragDropTargetElement(page, target, q1);

  assert.equal(
    dragTarget.moveTo(panel, false, true),
    true,
    "Move 1. You can move a question here"
  );
  assert.equal(page["koRows"]().length, 2, "Move 1. There are 2 rows");
  assert.equal(
    page["koRows"]()[0]["koElements"]()[0].name,
    "q1",
    "Move 1. The q1 is in the first row"
  );
  assert.equal(
    page["koRows"]()[1]["koElements"]()[0].name,
    "panel1",
    "Move 1. The panel1 is in the last row"
  );
  assert.equal(panel["koRows"]().length, 1, "Move 1. The panel has one rows");

  dragTarget.doDrop();
  assert.equal(page.elements.length, 2, "There are 2 elements on the page");
  assert.equal(page.elements[0].name, "q1", "The first element is 'q1'");
});

QUnit.test("Add item into page", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("page1");
  var target = new Survey.Panel("panel1");

  var dragTarget = new DragDropTargetElement(page, target, null);

  assert.equal(
    dragTarget.moveTo(page, false),
    true,
    "You can move a panel here"
  );
  assert.equal(page["koRows"]().length, 1, "There are one in the page");
  dragTarget.doDrop();
  assert.equal(page.elements.length, 1, "There is one element on the page");
  assert.equal(
    page.elements[0].name,
    "panel1",
    "The first element is 'panel1'"
  );
});
QUnit.test("Move item into empty paneldynamic", function(assert) {
  var survey = new Survey.Survey();
  var page = <Survey.Page>survey.addNewPage("page1");
  var panelDynamic = page.addNewQuestion("paneldynamic", "panel1");
  if (!panelDynamic) {
    assert.equal(true, true, "TODO remove-");
    return;
  }
  assert.equal(
    panelDynamic["template"].elements.length,
    0,
    "There is no questions in the template"
  );
  var target = new Survey.QuestionText("q1");
  var dragTarget = new DragDropTargetElement(page, target, null);
  assert.equal(
    dragTarget.moveTo(panelDynamic["template"], true),
    true,
    "You can move a question here"
  );
  dragTarget.doDrop();
  assert.equal(
    panelDynamic["template"].elements.length,
    1,
    "There are two questions in the template"
  );
  assert.equal(
    panelDynamic["template"].elements[0].name,
    "q1",
    "It is the 'q1' question"
  );
});
