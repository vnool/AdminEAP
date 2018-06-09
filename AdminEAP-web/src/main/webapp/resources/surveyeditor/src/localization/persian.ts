import { editorLocalization } from "../editorLocalization";

var persianStrings = {
  //survey templates
  survey: {
    edit: "ویرایش",
    dropQuestion: "لطفا از جعبه ابزار سوالی در اینجا قرار دهید",
    copy: "کپی",
    addToToolbox: "افزودن به جعبه ابزار",
    deletePanel: "حذف پنل",
    deleteQuestion: "حذف سوال",
    convertTo: "تبدیل به"
  },
  //questionTypes
  qt: {
    checkbox: "چند انتخابی",
    comment: "نظر",
    dropdown: "لیست انتخابی",
    file: "فایل",
    html: "Html",
    matrix: "ماتریس (تک انتخابی)",
    matrixdropdown: "ماتریس (چند انتخابی)",
    matrixdynamic: "ماتریس (سطرهای داینامیک)",
    multipletext: "متن چند خطی",
    panel: "پنل",
    paneldynamic: "پنل (پنل های داینامیک)",
    radiogroup: "تک انتخابی",
    rating: "رتبه بندی",
    text: "متن تک خطی",
    boolean: "صحیح و غلط",
    expression: "توصیفی"
  },
  //Strings in Editor
  ed: {
    survey: "نظرسنجی",
    editSurvey: "ویرایش نظرسنجی",
    addNewPage: "درج صفحه جدید",
    deletePage: "حذف صفحه",
    editPage: "ویرایش صفحه",
    newPageName: "صفحه",
    newQuestionName: "سوال",
    newPanelName: "پنل",
    testSurvey: "پیش نمایش",
    testSurveyAgain: "پیش نمایش مجدد",
    testSurveyWidth: "عرض پرسشنامه: ",
    embedSurvey: "کد پرسشنامه",
    saveSurvey: "ذخیره نظرسنجی",
    designer: "طراح پرسشنامه",
    jsonEditor: "ویرایشگر JSON",
    undo: "بازگردانی",
    redo: "بازانجام",
    options: "انتخاب ها",
    generateValidJSON: "تولید کد معتبر JSON",
    generateReadableJSON: "تولید کد خوانا JSON",
    toolbox: "جعبه ابزار",
    delSelObject: "حذف مورد انتخابی",
    editSelObject: "ویرایش مورد انتخابی",
    correctJSON: "کد JSON را تصحیح کنید",
    surveyResults: "نتایج نظرسنجی: ",
    modified: "تغییر داده شده",
    saving: "در حال ذخیره سازی",
    saved: "ذخیره شد"
  },
  //Property names in table headers
  pel: {
    isRequired: "اجباری؟"
  },
  //Property Editors
  pe: {
    apply: "اعمال",
    ok: "تایید",
    cancel: "لغو",
    reset: "بازنشانی",
    close: "بستن",
    delete: "حذف",
    addNew: "افزودن",
    removeAll: "حذف همه",
    edit: "ویرایش",
    empty: "<خالی>",
    fastEntry: "تکمیل سریع",
    formEntry: "تکمیل فرم",
    testService: "بررسی سرویس",
    conditionHelp:
      "لطفا یک مقدار بولین توصیفی وارد کنید که صحیح یا غلط را برگرداند تا صفحه سوالات نمایش داده شود. برای مثال: {question1} = 'value1' or ({question2} * {question4}  > 20 and {question3} < 5)",
    expressionHelp:
      "لطفا یک عبارت توصیفی را وارد کنید. شما ممکن است از کروشه برای دسترسی به مقدار سوالات استفاده کنید. برای مثال: {question1} = 'value1' or ({question2} = 3 and {question3} < 5)",

    aceEditorHelp: "برای مشاهده نکات تکمیلی ctrl+space را بفشارید",
    aceEditorRowTitle: "سطر فعلی",
    aceEditorPanelTitle: "پنل فعلی",
    showMore: "برای اطلاعات بیشتر لطفا سند راهنما را مطالعه کنید",
    assistantTitle: "سوالات موجود:",

    propertyIsEmpty: "لطفا یک مقدار وارد کنید",
    value: "مقدار",
    text: "متن",
    columnEdit: "ویرایش ستون: {0}",
    itemEdit: "ویرایش آیتم: {0}",

    url: "URL",
    path: "Path",
    valueName: "نام مقدار",
    titleName: "نام عنوان",

    hasOther: "دارای آیتم دیگر",
    name: "نام",
    title: "عنوان",
    cellType: "نوع سلول",
    colCount: "تعداد ستون",
    choicesOrder: "ترتیب گزینه را انتخاب کنید",
    visible: "نمایش داده شود؟",
    isRequired: "ضروری است؟",
    startWithNewLine: "با سطر جدید شروع شود؟",
    rows: "تعداد سطر",
    placeHolder: "نگهدارنده متن",
    showPreview: "پیش نمایش تصویر نشان داده شود؟",
    storeDataAsText: "ذخیره کردن محتوای فایل در JSON به عنوان متن",
    maxSize: "حداکثر سایز به بایت",
    imageHeight: "ارتفاع تصویر",
    imageWidth: "عرض تصویر",
    rowCount: "تعداد سطر",
    addRowText: "متن دکمه درج سطر",
    removeRowText: "متن دکمه حذف سطر",
    minRateDescription: "توضیح حداقل امتیاز",
    maxRateDescription: "توضیح حداکثر امتیاز",
    inputType: "نوع ورودی",
    optionsCaption: "نوشته انتخاب ها",
    defaultValue: "مقدار پیش فرض",

    surveyEditorTitle: "ویرایش نظرسنجی",
    qEditorTitle: "ویرایش سوال: {0}",
    //survey
    showTitle: "نمایش/پنهان کردن عنوان",
    locale: "زبان پیش فرض",
    mode: "حالت (ویرایش/خواندن)",
    clearInvisibleValues: "پاکسازی مقادیر پنهان",
    cookieName: "نام کوکی (به منظور جلوگیری از اجرای دوباره نظرسنجی)",
    sendResultOnPageNext: "ارسال نتایج نظرسنجی در صفحه بعدی",
    storeOthersAsComment: "ذخیره مقدار 'سایر' در فیلد جداگانه",
    showPageTitles: "نمایش عنوان صفحات",
    showPageNumbers: "نمایش شماره صفحات",
    pagePrevText: "متن دکمه صفحه قبلی",
    pageNextText: "متن دکمه صفحه بعدی",
    completeText: "متن دکمه تکمیل نظرسنجی",
    startSurveyText: "متن دکمه شروع نظرسنجی",
    showNavigationButtons: "نمایش دکمه های ناوبری (ناوبری پیش فرض)",
    showPrevButton: "نمایش دکمه قبلی (کاربر ممکن است به صفحه قبل برگردد)",
    firstPageIsStarted: "صفحه اول در نظرسنجی نقطه آغازین آن است.",
    showCompletedPage: "نمایش صفحه اتمام نظرسنجی در پایان (completedHtml)",
    goNextPageAutomatic:
      "با پاسخگویی به تمام سوالات، به صورت اتوماتیک به صفحه بعد برود",
    showProgressBar: "نمایش نشانگر پیشرفت",
    questionTitleLocation: "محل عنوان سوال",
    requiredText: "سوالات نشان دار اجباری هستند",
    questionStartIndex: "نمایه شروع سوالات (۱،۲ یا a و b)",
    showQuestionNumbers: "نمایش شماره های سوالات",
    questionTitleTemplate:
      "قالب عنوان سوال، به صورت پیش فرض: '{no}. {require} {title}'",
    questionErrorLocation: "محل خطای سوال",
    focusFirstQuestionAutomatic: "تمرکز بر روی اولین سوال با تغییر صفحه",
    questionsOrder: "ترتیب المان ها در صفحه",
    maxTimeToFinish: "نهایت زمان برای اتمام نظرسنجی",
    maxTimeToFinishPage: "نهایت زمان برای اتمام این صفحه نظرسنجی",
    showTimerPanel: "نمایش پنل زمان سنج",
    showTimerPanelMode: "نمایش حالت پنل زمان سنج",
    renderMode: "حالت رندر",
    allowAddPanel: "اجازه افزودن پنل",
    allowRemovePanel: "اجازه حذف پنل",
    panelAddText: "متن افزودن پنل",
    panelRemoveText: "متن حذف پنل",
    isSinglePage: "نمایش تمام المان ها در یک صفحه",

    tabs: {
      general: "عمومی",
      fileOptions: "انتخاب ها",
      html: "ویرایشگر HTML",
      columns: "ستون ها",
      rows: "سطرها",
      choices: "انتخاب ها",
      visibleIf: "نمایش در صورت",
      rateValues: "مقادیر رتبه بندی",
      choicesByUrl: "انتخاب ها از وب",
      matrixChoices: "انتخاب های پیشفرض",
      multipleTextItems: "فیلدهای متنی",
      validators: "اعتبارسنجی ها",
      navigation: "ناوبری",
      question: "سوال",
      completedHtml: "HTML صفحه تکمیل نظرسنجی",
      loadingHtml: "HTML بارگزاری",
      timer: "زمان سنج/کوئیز",
      triggers: "اجرا کننده",
      templateTitle: "عنوان قالب"
    },
    editProperty: "ویرایش خصوصیت '{0}'",
    items: "[ آیتم ها: {0} ]",

    enterNewValue: "لطفا یک مقدار وارد کنید",
    noquestions: "سوالی در پرسشنامه درج نشده",
    createtrigger: "اجرا کننده ای بسازید",
    triggerOn: "در ",
    triggerMakePagesVisible: "صفحات را قابل نمایش کن:",
    triggerMakeQuestionsVisible: "سوالات را قابل نمایش کن:",
    triggerCompleteText: "پرسشنامه را تکمیل کن اگر موفق بود.",
    triggerNotSet: "اجرا کننده تنظیم نشده.",
    triggerRunIf: "اجرا در صورت",
    triggerSetToName: "تعییر مقدار از: ",
    triggerSetValue: "به: ",
    triggerIsVariable: "عدم درج متغییر در نتایج پرسشنامه"
  },
  //Property values
  pv: {
    true: "صحیح",
    false: "نادرست"
  },
  //Operators
  op: {
    empty: "خالی باشد",
    notempty: "خالی نباشد",
    equal: "مساوی باشد",
    notequal: "مساوی نباشد",
    contains: "شامل",
    notcontains: "شامل نباشد",
    greater: "بزرگتر",
    less: "کوچکتر",
    greaterorequal: "بزرگتر یا مساوی",
    lessorequal: "کوچکتر یا مساوی"
  },
  //Embed window
  ew: {
    angular: "استفاده از نسخه Angular",
    jquery: "استفاده از نسخه jQuery",
    knockout: "استفاده از نسخه ناک اوت",
    react: "استفاده از نسخه React",
    vue: "استفاده از نسخه Vue",
    bootstrap: "برای فریم ورک بوتسترپ",
    standard: "بدون بوتسترپ",
    showOnPage: "نمایش نظرسنجی در یک صفحه",
    showInWindow: "نمایش نظرسنجی در یک پنجره",
    loadFromServer: "بارگزاری JSON از سرور",
    titleScript: "اسکریپت و شیوه نمایش",
    titleHtml: "HTML",
    titleJavaScript: "جاوااسکریپت"
  },
  //Test Survey
  ts: {
    selectPage: "صفحه ای را برای آزمایش انتخاب کنید:"
  },
  validators: {
    answercountvalidator: "تعداد پاسخ",
    emailvalidator: "ایمیل",
    numericvalidator: "عدد",
    regexvalidator: "regex",
    textvalidator: "متن"
  },
  triggers: {
    completetrigger: "تکمیل نظرسنجی",
    setvaluetrigger: "تنظیم مقدار",
    visibletrigger: "تغییر وضعیت دیده شدن"
  },
  //Properties
  p: {
    name: "نام",
    title: { name: "عنوان", title: "اگر خالی باشد مانند نام درج می شود" },
    survey_title: { name: "عنوان", title: "در تمام صفحات دیده می شود" },
    page_title: { name: "عنوان", title: "عنوان صفحه" }
  }
};

editorLocalization.locales["fa"] = persianStrings;
