**survey.js.editor** is the visual survey builder / form builder for [SurveyJS](https://github.com/surveyjs/surveyjs). It uses JSON to store the survey metadata.

[![Build Status](https://travis-ci.org/surveyjs/editor.svg?branch=master)](https://travis-ci.org/surveyjs/editor)
[![npm package](https://badge.fury.io/js/surveyjs-editor.svg)](https://www.npmjs.com/package/surveyjs-editor)

## Download

Dowload the latest version as zip file [Download](https://github.com/surveyjs/editor/releases)

Install the library using npm.

```
npm install surveyjs-editor
```

Or use Azure CDN:

- https://surveyjs.azureedge.net/{version-number}/surveyeditor.js
- https://surveyjs.azureedge.net/{version-number}/surveyeditor.css

## Include into your project

### Explore examples

To learn how to include the visual survey builder / form builder on your page and explore examples of using, please go to [surveyjs.io/Examples/Builder](https://surveyjs.io/Examples/Builder) web site

### Read documentation

To find out more about the library concepts and the you may customize it, please read [our documentation](https://surveyjs.io/Documentation/Builder)

### Use quickstart repos

[Angular 2](https://github.com/surveyjs/surveyjs_angular_quickstart), [Angular CLI](https://github.com/surveyjs/surveyjs_angular_cli), [React](https://github.com/surveyjs/surveyjs_react_quickstart), [Vue](https://github.com/surveyjs/surveyjs_vue_quickstart)

## See editor in action

See the visual editor in [action](http://surveyjs.io/Survey/Builder/).

## Building surveyjs Editor from sources

To build library yourself:

1.  **Clone the repo from GitHub**

    ```
    git clone https://github.com/surveyjs/editor.git
    cd surveyjs.editor
    ```

2.  **Acquire build dependencies.** Make sure you have [Node.js](http://nodejs.org/) installed on your workstation. You need a version of Node.js greater than 6.0.0 and npm greater than 2.7.0

    ```
    npm install
    ```

3.  **Build the library**

    ```
    npm run build_prod
    ```

    After that you should have the library at 'package' directory.

4.  **Run unit tests**
    ```
    karma start
    ```
    This command will run unit tests usign [Karma](https://karma-runner.github.io/0.13/index.html)

## Dependencies

The library depends on: [surveyjs](http://surveyjs.io/Library/) and [knockoutjs](http://knockoutjs.com)
Optionally you may use [bootstrap](http://getbootstrap.com), [ace editor](https://ace.c9.io/) and [select2](https://select2.org/)

## License

The SurveyJS Builder (Editor) is NOT FREE for commercial using. Please find more about licensing the product [here](http://surveyjs.io/Licenses).
