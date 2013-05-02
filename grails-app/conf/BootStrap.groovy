import finder.*

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
				def batch1 = new Batch(name:"batch1",item:item1).save(failOnError: true, flush: true)
    	}
			


		}
  }
  def destroy = {
  }
}
