import foodprint.*

class BootStrap {

  def init = { servletContext ->

		environments {
			def role1 = Role.findOrSaveByAuthority('ROLE_ADMIN')
			def user1 = User.findByUsername('admin')

			if (!user1) {
			    user1 = new User(username: 'admin', password: 'admin', enabled: true, works: true).save(failOnError: true, flush: true)
			    
			    UserRole.create(user1, role1)

			}

			development {
				def item1 = new Item(name:"item1").save(failOnError: true, flush: true)
				def item2 = new Item(name:"item2").save(failOnError: true, flush: true)
				def batch1 = new Batch(name:"batch1",item:item1).save(failOnError: true, flush: true)
				def batch2 = new Batch(name:"batch2",item:item1,dueDate:86400000).save(failOnError: true, flush: true)
				def workstation1 = new Workstation(name:"workstation1",title:"wstitle1").save(failOnError: true, flush: true)
				def workstation2 = new Workstation(name:"workstation2",title:"wstitle2").save(failOnError: true, flush: true)


				def itemImage1 = new ItemImage(name:"itemImage1.jpg",item:item1).save(failOnError: true, flush: true)
				def itemImage2 = new ItemImage(name:"itemImage2.jpg",item:item1).save(failOnError: true, flush: true)
    	}
			


		}
  }
  def destroy = {
  }
}
