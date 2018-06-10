if (!window["%hammerhead%"]) {
   var BASEURL = '../../..'; 

   var pageID = "";
   if(location.hash.length > 3) {
     pageID =location.hash.substr(1);
   }
    //   var titleAdorner = {
    //     getCss: () => {
    //       return "mu_title";
    //     },
    //     afterRender: (domEl, model) => {
    //       var decoration = document.createElement("div");
    //       decoration.innerHTML = "";
    //       domEl.appendChild(decoration);
    //     }
    //   };

    //   SurveyEditor.registerAdorner("title", titleAdorner);

    SurveyEditor.editorLocalization.currentLocale = "zh-cn";

    // SurveyEditor.removeAdorners(["mainRoot"]);

    // Survey.Survey.cssType = "bootstrap";
    // Survey.defaultBootstrapCss.navigationButton = "btn btn-green";
    // SurveyEditor.editorLocalization.currentLocale = "es";\
    // SurveyEditor.StylesManager.applyTheme("winter");
    var editor = new SurveyEditor.SurveyEditor("editorElement");
    // SurveyEditor.StylesManager.applyTheme("orange");
    //editor.surveyId = '5af48e08-a0a5-44a5-83f4-1c90e8e98de1';
    //editor.surveyPostId = '3ce10f8b-2d8a-4ca2-a110-2994b9e697a1';

    editor.saveSurveyFunc = function(saveNo, callback) {
          var saveurl = BASEURL+"/papers/savepaper" ;
          var data = "obj=" + JSON.stringify({"id": pageID, "pages": editor.text});

          $.ajax({
            url: saveurl,
            type: "POST",
            data: encodeURI(data),
            success: function (data) {
                callback(saveNo, data.isSuccess);
            },
            error: function (xhr, ajaxOptions, thrownError) {
                callback(saveNo, false);
                alert(thrownError);
            }
        });


        callback(saveNo, true);
    };



    
  //   setTimeout(function(){

  //   );
  // },2000);

    //editor.loadSurvey("b2b56b2c-ad9e-4951-8f0e-c246d6b6a52a");
    // editor.showOptions = true;
  editor.isAutoSave = true;
  editor.showState = true;
    //editor.loadSurvey("a0f7f132-eee4-42e4-b8c8-f8b16840a478");
    //editor.loadSurvey("65c74d4a-3b16-412f-8200-9ac53c8f5c0b");

   // ko.applyBindings(new SurveyEditor.SurveysManager("https://dxsurvey.com", "a797f29b53f8455e8b3ef317f8904dac", editor), document.getElementById("manage"));

    window.editor = editor;

    load_data(); //加载历史数据

}

function load_data(){
   if(pageID){//get old data

      var dataurl = BASEURL+"/papers/get/"+ pageID;
      
      $.get(dataurl, function(result){
         if(result.pages!=undefined && result.pages.length > 10){
            window.editor.text = result.pages;
         }
          
      });
    }else{
        window.editor.text = JSON.stringify({
           "pages": [
            {
             "name": "页面1",
             "elements": [
              {
               "type": "html",
               "name": "问题1",
               "html": "<h3>这里输入试卷标题</h3>"
              }
             ]
            }
           ] 
       });
   } 
}

 