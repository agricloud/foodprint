class UrlMappings {

	static mappings = {
		"/report/query" (controller: "reportViewer", action: "query")
		"/report/$name/$action" (controller: "reportViewer")
		"/report/$name" (controller: "reportViewer", action: "index")
		"/report" (controller: "reportViewer", action: "search")

        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }


		"/"(controller: "home", action: "index")
		"500"(view:'/error')



	}
}
