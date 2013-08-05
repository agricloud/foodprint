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
				def batch2 = new Batch(name:"batch2",item:item1).save(failOnError: true, flush: true)
				def workstation1 = new Workstation(name:"01",title:"workstation1").save(failOnError: true, flush: true)
				def workstation2 = new Workstation(name:"02",title:"workstation2").save(failOnError: true, flush: true)
				def param1= new Param(title:"water",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param2 = new Param(title:"temperature",defaultValue:"45",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def report1=new Report(name:"01",title:"water").save(failOnError: true, flush: true)
				def report2=new Report(name:"02",title:"temperature").save(failOnError: true, flush: true)
				def reportparam1=new  ReportParams (report:report1,param:param1,workstation:workstation1).save(failOnError: true, flush: true)
				def reportparam2=new  ReportParams (report:report2,param:param2,workstation:workstation2).save(failOnError: true, flush: true)

				def itemImage1 = new ItemImage(name:"itemImage1.jpg",item:item1).save(failOnError: true, flush: true)
				def itemImage2 = new ItemImage(name:"itemImage2.jpg",item:item1).save(failOnError: true, flush: true)
    	}
			


		}
  }
  def destroy = {
  }
}
