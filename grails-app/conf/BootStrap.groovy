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
    	 }
    }
    def destroy = {
    }
}
