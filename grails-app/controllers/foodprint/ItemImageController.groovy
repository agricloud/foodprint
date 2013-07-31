package foodprint


class ItemImageController {

	def listAll = {
		
		def itemImages=ItemImage.list()



		render (contentType: 'text/json') {
      [
				itemImages: itemImages,
				total: itemImages.size()
			]
		}
		
	}
    def show = { Long id ->

    	println id

	    def item=Item.findById(id)
			
			def itemImages=ItemImage.findAllByItem(item)

			render (contentType: 'text/json') {
	      [
					itemImages: itemImages
				]
			}
			
		}


}
