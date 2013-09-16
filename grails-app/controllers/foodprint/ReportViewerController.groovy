package foodprint

class ReportViewerController {

    def index() { 

        [itemInstanceList: Item.list(params), itemInstanceTotal: Item.count()]

    }
}
