class UrlMappings {

	static mappings = {
		"/report/$name/$action" (controller: "reportViewer")

        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
            }
        }


		"/"(controller: "home", action: "index")
		"500"(view:'/error')



	}
}
