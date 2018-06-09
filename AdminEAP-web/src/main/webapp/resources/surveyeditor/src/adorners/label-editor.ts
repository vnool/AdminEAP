import * as ko from "knockout";
import { registerAdorner } from "../surveyjsObjects";
import { TitleInplaceEditor } from "./title-editor";

import "./label-editor.scss";

export var labelAdorner = {
  getMarkerClass: model => {
    if (model.getType() === "boolean") {
      return "label_editable";
    }
    return "";
  },
  afterRender: (elements: HTMLElement[], model, editor) => {
    var decoration = document.createElement("span");
    decoration.innerHTML =
      "<title-editor params='name: \"label\", model: model, editor: editor'></title-editor>";
    elements[0].onclick = e => e.preventDefault();
    elements[0].appendChild(decoration);
    ko.applyBindings({ model: model, editor: editor }, decoration);
  }
};

registerAdorner("label", labelAdorner);
