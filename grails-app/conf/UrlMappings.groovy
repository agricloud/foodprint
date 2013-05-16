class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		
		"/rest/$controller/$id"{
			action = [GET: "show", PUT:"update", DELETE:"delete"]
			constraints {
				// apply constraints here
			}
		}
		
		"/rest/$controller"{
			action = [GET:"listAll", POST: "create"]
			constraints {
				// apply constraints here
			}
		}


		"/"(controller: "home", action: "index")
		"500"(view:'/error')



	}
}
