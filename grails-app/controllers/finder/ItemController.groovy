package finder
import grails.converters.JSON

class ItemController {

	def listAll = {
		
		def items=Item.list()



		render (contentType: 'text/json') {
      [
				items: items,
				total: items.size()
			]
		}
		
	}

	def show = { Long id ->
		
		def item=Item.findById(id)

		println "show"
		println item as JSON

		render (contentType: 'text/json') {
      [
				item: item
			]
		}
		
	}
	
	def create = {
		// log.info request.JSON
		// def menu=Menu.findByFunctionId(request.JSON.menuId)
		// def userGroup=UserGroup.findByUserGroupId(request.JSON.userGroupId)

		// log.info menu
		// log.info userGroup
		
		// def item=new MenuGroup()
		// item.menu=menu;
		// item.userGroup=userGroup;
		
		// item.save()
  //       render (contentType: 'text/json') {
  //           [
  //               success: item.hasErrors()==false
  //           ]
  //       }

	}
	def update = {
		// def menu=Menu.findByFunctionId(request.JSON.menuId)
		// def userGroup=UserGroup.findByUserGroupId(request.JSON.userGroupId)
		// def item=MenuGroup.findById(request.JSON.id)
		// if (!item) {
		// 	render (contentType: 'text/json') {
		// 		[
		// 			success: false
		// 		]
		// 	}
		// }else {
		
		// 	item.menu=menu
		// 	item.userGroup=userGroup
		// 	item.save()
		// 	render (contentType: 'text/json') {
		// 		[
		// 			success: item.hasErrors()==false
		// 		]
		// 	}
		// }
	}
	
	def delete={
		

		// def item=MenuGroup.findById(params.id)

		// if (!item) {
		// 	render (contentType: 'text/json') {
		// 		[
		// 			success: false
		// 		]
		// 	}
		// }else {
		// 	item.delete();
			
		// 	render (contentType: 'text/json') {
		// 		[
		// 			success: item.hasErrors()==false
		// 		]
		// 	}
		// }
	}
}
