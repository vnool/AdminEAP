import { editorLocalization } from "../editorLocalization";

var portugueseTranslation = {
  //survey templates
  survey: {
    dropQuestion: "Por favor arraste uma pergunta aqui.",
    copy: "Copiar",
    addToToolbox: "Adicionar à toolbox",
    deletePanel: "Remover Painel",
    deleteQuestion: "Remover Pergunta"
  },
  //questionTypes
  qt: {
    checkbox: "Checkbox",
    comment: "Comentário",
    dropdown: "Dropdown",
    file: "Arquivo",
    html: "Html",
    matrix: "Matriz (opção única)",
    matrixdropdown: "Matriz (multiplas opções)",
    matrixdynamic: "Matriz (linhas dinâmicas)",
    multipletext: "Texto múltiplo",
    panel: "Painel",
    radiogroup: "Radiogroup",
    rating: "Rating",
    text: "Texto único"
  },
  //Strings in Editor
  ed: {
    addNewPage: "Adicionar Nova Página",
    newPageName: "página",
    newQuestionName: "pergunta",
    newPanelName: "painel",
    testSurvey: "Testar pesquisa",
    testSurveyAgain: "Testar pesquisa novamente",
    testSurveyWidth: "Tamanho do pesquisa: ",
    embedSurvey: "Incorporar Pesquisa",
    saveSurvey: "Salvar Pesquisa",
    designer: "Designer de Pesquisa",
    jsonEditor: "Editor de JSON",
    undo: "Desfazer",
    redo: "Refazer",
    options: "Opções",
    generateValidJSON: "Gerar JSON válido",
    generateReadableJSON: "Gerar JSON legível",
    toolbox: "Toolbox",
    delSelObject: "Apagar objeto selecionado",
    correctJSON: "Por favor corrija o JSON.",
    surveyResults: "Resultado da pesquisa: "
  },
  //Property names in table headers
  pel: {
    isRequired: "Obrigatório?"
  },
  //Property Editors
  pe: {
    apply: "Aplicar",
    ok: "OK",
    cancel: "Cancelar",
    reset: "Limpar",
    close: "Fechar",
    delete: "Apagar",
    addNew: "Adicionar Novo",
    removeAll: "Remover Todos",
    edit: "Editar",
    empty: "<vazio>",
    fastEntry: "Entrada Rápida",
    formEntry: "Entrada com formulário",
    testService: "Testar o serviço",
    expressionHelp:
      "Por favor informe uma expressão boleana. Ela deve retornar verdadeiro para manter a pergunta/página visível. Por exemplo: {´pergunta1} = 'valor1' or ({pergunta2} = 3 and {pergunta3} < 5)",

    propertyIsEmpty: "Por favor informe um valor na propriedade",
    value: "Valor",
    text: "Texto",
    columnEdit: "Editar coluna: {0}",
    itemEdit: "Editar item: {0}",

    hasOther: "Tem outro item",
    name: "Nome",
    title: "Título",
    cellType: "Tipo de célula",
    colCount: "Contagem de células",
    choicesOrder: "Selecione a ordem das alternativas",
    visible: "É visível?",
    isRequired: "É obrigatório?",
    startWithNewLine: "Começa com uma nova linha?",
    rows: "Contagem de linhas",
    placeHolder: "Texto de referência",
    showPreview: "Mostra pré-visualização de imagem?",
    storeDataAsText: "Gravar conteúdo de arquivo no resultado JSON como texto",
    maxSize: "Tamanho máximo de arquivo em bytes",
    imageHeight: "Altura da imagem",
    imageWidth: "Largura da imagem",
    rowCount: "Contagem de linhas",
    addRowText: "Texto do botão para adicionar linhas",
    removeRowText: "Texto do botão para remover linhas",
    minRateDescription: "Descrição de qualificação mínima",
    maxRateDescription: "Descrição de qualificação máxima",
    inputType: "Tipo de entrada",
    optionsCaption: "Título de opção",

    qEditorTitle: "Editar pergunta: {0}",
    tabs: {
      general: "Geral",
      fileOptions: "Opções",
      html: "Editor Html",
      columns: "Colunas",
      rows: "Linhas",
      choices: "Opções",
      visibleIf: "Visível se",
      rateValues: "Valores de qualificação",
      choicesByUrl: "Opções com origem na Web",
      matrixChoices: "Opções padrão",
      multipleTextItems: "Entradas de texto",
      validators: "Validadores"
    },

    editProperty: "Editar propriedade '{0}'",
    items: "[ Items: {0} ]",

    enterNewValue: "Por favor, informe o valor.",
    noquestions: "Não há nenhuma pergunta na pesquisa.",
    createtrigger: "Por favor, crie um gatilho",
    triggerOn: "Ligado ",
    triggerMakePagesVisible: "Tornar páginas visíveis:",
    triggerMakeQuestionsVisible: "Tornar perguntas visíves:",
    triggerCompleteText: "Completar a pesquisa se obtiver êxito.",
    triggerNotSet: "O gatilho não está definido",
    triggerRunIf: "Executar se",
    triggerSetToName: "Mudar o valor de: ",
    triggerSetValue: "para: ",
    triggerIsVariable: "Não colocar a variável no resultado da pesquisa."
  },
  //Operators
  op: {
    empty: "está vazio",
    notempty: "não está vazio",
    equal: "é igual",
    notequal: "não é igual",
    contains: "contém",
    notcontains: "não contém",
    greater: "maior",
    less: "menor",
    greaterorequal: "maior ou igual",
    lessorequal: "menor ou igual"
  },
  //Embed window
  ew: {
    angular: "Usar versão Angular",
    jquery: "Usar versão jQuery",
    knockout: "Usar versão Knockout",
    react: "Usar versão React",
    vue: "Usar versão Vue",
    bootstrap: "Para framework bootstrap",
    standard: "Sem bootstrap",
    showOnPage: "Mostrar pesquisa em uma página",
    showInWindow: "Mostrar pesquisa em uma janela",
    loadFromServer: "Carregar JSON da pesquisa de um servidor",
    titleScript: "Scripts e estilos",
    titleHtml: "HTML",
    titleJavaScript: "JavaScript"
  },
  //Properties
  p: {
    name: "nome",
    title: { name: "título", title: "Deixar vazio se for o mesmo que 'Nome'" },
    survey_title: { name: "título", title: "Será mostrado em cada página." },
    page_title: { name: "título", title: "Título de página" }
  }
};

editorLocalization.locales["pt"] = portugueseTranslation;
