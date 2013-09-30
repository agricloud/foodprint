class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.${format})?"(parseRequest:true){
            constraints {
                // apply constraints here
            }
        }

		
		"/rest/$controller/$id?(.${format})?"(parseRequest:true){
			action = [GET: "show", PUT:"update", DELETE:"delete"]
			constraints {
				// apply constraints here
			}
		}
		
		"/rest/$controller/"(parseRequest:true){
			action = [GET:"listJson", POST: "create", PUT:"update", DELETE:"delete"]
			constraints {
				// apply constraints here
			}
		}


		"/"(controller: "home", action: "index")
		"500"(view:'/error')



	}
}
