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
				def item1 = new Item(name:"01",title:"柳丁").save(failOnError: true, flush: true)
				def item2 = new Item(name:"02",title:"橘子").save(failOnError: true, flush: true)
				def item3 = new Item(name:"03",title:"柚子").save(failOnError: true, flush: true)
				def batch1 = new Batch(name:"batch1",item:item1).save(failOnError: true, flush: true)
				def batch2 = new Batch(name:"batch2",item:item1).save(failOnError: true, flush: true)
				def batch3 = new Batch(name:"batch3",item:item2).save(failOnError: true, flush: true)
				def workstation1 = new Workstation(name:"workstation1",title:"檢驗站01").save(failOnError: true, flush: true)
				def workstation2 = new Workstation(name:"workstation2",title:"檢驗站02").save(failOnError: true, flush: true)
				def param1= new Param(title:"water",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param2 = new Param(title:"temperature",defaultValue:"45",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param100 = new Param(title:"益多松",defaultValue:"0.0",paramType:ParamType.STRING).save(failOnError: true, flush: true)
				def param101 = new Param(title:"芬殺蟎",defaultValue:"0.5",paramType:ParamType.STRING).save(failOnError: true, flush: true)
				def param102 = new Param(title:"芬普蟎",defaultValue:"0.5",paramType:ParamType.STRING).save(failOnError: true, flush: true)

				def report1=new Report(name:"01",title:"water").save(failOnError: true, flush: true)
				def report2=new Report(name:"02",title:"temperature").save(failOnError: true, flush: true)
				def report100=new Report(name:"03",title:"檢驗履歷").save(failOnError: true, flush: true)
				def reportparam1=new  ReportParams (report:report1,param:param1,workstation:workstation1).save(failOnError: true, flush: true)
				def reportparam2=new  ReportParams (report:report2,param:param2,workstation:workstation2).save(failOnError: true, flush: true)
				def reportparam100=new  ReportParams (report:report100,param:param100,workstation:workstation1,item:item1).save(failOnError: true, flush: true)
				def reportparam101=new  ReportParams (report:report100,param:param101,workstation:workstation1,item:item1).save(failOnError: true, flush: true)
				def reportparam102=new  ReportParams (report:report100,param:param102,workstation:workstation2,item:item1).save(failOnError: true, flush: true)

				def itemRoute1=new ItemRoute(item:item1,sequence:1,workstation:workstation1).save(failOnError: true, flush: true)
				def itemRoute2=new ItemRoute(item:item1,sequence:2,workstation:workstation1).save(failOnError: true, flush: true)
				def itemRoute3=new ItemRoute(item:item1,sequence:3,workstation:workstation2).save(failOnError: true, flush: true)
				def itemRoute4=new ItemRoute(item:item1,sequence:4,workstation:workstation1).save(failOnError: true, flush: true)
				def itemRoute5=new ItemRoute(item:item2,sequence:1,workstation:workstation1).save(failOnError: true, flush: true)
				def itemRoute6=new ItemRoute(item:item2,sequence:2,workstation:workstation2).save(failOnError: true, flush: true)
				def itemRoute7=new ItemRoute(item:item3,sequence:1,workstation:workstation1).save(failOnError: true, flush: true)
				def itemRoute8=new ItemRoute(item:item3,sequence:2,workstation:workstation2).save(failOnError: true, flush: true)

				def itemImage1 = new ItemImage(name:"itemImage1.jpg",item:item1).save(failOnError: true, flush: true)
				def itemImage2 = new ItemImage(name:"itemImage2.jpg",item:item1).save(failOnError: true, flush: true)
    	}
			


		}
  }
  def destroy = {
  }
}
