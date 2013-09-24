import foodprint.*

class BootStrap {

  def init = { servletContext ->

		environments {
			def role1 = Role.findOrSaveByAuthority('ROLE_ADMIN')
			def user1 = User.findByUsername('admin')

			if (!user1) {
			    user1 = new User(username: 'admin', password: 'admin', enabled: true).save(failOnError: true, flush: true)
			    
			    UserRole.create(user1, role1)

			}

			development {
				def item1 = new Item(name:"item1",title:"柳丁").save(failOnError: true, flush: true)
				def item2 = new Item(name:"item2",title:"橘子").save(failOnError: true, flush: true)
				def item3 = new Item(name:"item3",title:"柚子").save(failOnError: true, flush: true)
				def batch1 = new Batch(name:"batch1",item:item1).save(failOnError: true, flush: true)
				def batch2 = new Batch(name:"batch2",item:item1).save(failOnError: true, flush: true)
				def batch3 = new Batch(name:"batch3",item:item2).save(failOnError: true, flush: true)
				def workstation1 = new Workstation(name:"workstation1",title:"檢驗站01").save(failOnError: true, flush: true)
				def workstation2 = new Workstation(name:"workstation2",title:"檢驗站02").save(failOnError: true, flush: true)


				def operation1=new Operation(name:"operation1",title:"施肥").save(failOnError: true, flush: true)
				def operation2=new Operation(name:"operation2",title:"翻土").save(failOnError: true, flush: true)
				def operation3=new Operation(name:"operation3",title:"病蟲害防治").save(failOnError: true, flush: true)
				def operation4=new Operation(name:"operation4",title:"檢驗").save(failOnError: true, flush: true)

				def param1= new Param(name:"param1",title:"Pack",defaultValue:"玻璃瓶[60 毫升/62g]",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param2= new Param(name:"param2",title:"Allergen",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param3= new Param(name:"param3",title:"StorageTemperature",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param4= new Param(name:"param4",title:"PerServing",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param5= new Param(name:"param5",title:"PackageCapacity",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param6= new Param(name:"param6",title:"ItemImage",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param7= new Param(name:"param7",title:"Calories",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param8= new Param(name:"param8",title:"Protein",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param9= new Param(name:"param9",title:"TotalFat",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param10= new Param(name:"param10",title:"SatFat",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param11= new Param(name:"param11",title:"TransFat",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param12= new Param(name:"param12",title:"Carbohydrate",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param13= new Param(name:"param13",title:"Sodium",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param14= new Param(name:"param14",title:"OtherNutritions",defaultValue:"100",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)

				def param22 = new Param(name:"temperature",title:"temperature",defaultValue:"45",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param100 = new Param(name:"A",title:"益多松",defaultValue:"0.0",paramType:ParamType.INTEGER).save(failOnError: true, flush: true)
				def param101 = new Param(name:"B",title:"芬殺蟎",defaultValue:false,paramType:ParamType.BOOLEAN).save(failOnError: true, flush: true)
				def param102 = new Param(name:"C",title:"芬普蟎",defaultValue:"0.5",paramType:ParamType.STRING).save(failOnError: true, flush: true)
				// def param103 = new Param(name:"D",title:"亞芬松",defaultValue:"[0.1,0.2,0.3,0.4,0.5]",paramType:ParamType.LIST).save(failOnError: true, flush: true)


				def report1=new Report(name:"report1",title:"營養標示履歷").save(failOnError: true, flush: true)
				def report2=new Report(name:"report2",title:"檢驗紀錄集").save(failOnError: true, flush: true)
				def report100=new Report(name:"report3",title:"成品檢驗報告集").save(failOnError: true, flush: true)

				def reportparam1=new  ReportParams (report:report1,param:param1,workstation:workstation1,operation:operation2).save(failOnError: true, flush: true)
				def reportparam2=new  ReportParams (report:report2,param:param2,workstation:workstation2,operation:operation2).save(failOnError: true, flush: true)
				def reportparam100=new  ReportParams (report:report100,param:param100,workstation:workstation1,operation:operation4,item:item1).save(failOnError: true, flush: true)
				def reportparam101=new  ReportParams (report:report100,param:param101,workstation:workstation1,operation:operation4,item:item1).save(failOnError: true, flush: true)
				def reportparam102=new  ReportParams (report:report100,param:param102,workstation:workstation2,operation:operation4,item:item1).save(failOnError: true, flush: true)
				// def reportparam103=new  ReportParams (report:report100,param:param103,workstation:workstation2,operation:operation4,item:item1).save(failOnError: true, flush: true)


				def batchReportDet1=new  BatchReportDet (batch:batch1,reportParams:reportparam1).save(failOnError: true, flush: true)
				def batchReportDet2=new  BatchReportDet (batch:batch1,reportParams:reportparam2).save(failOnError: true, flush: true)
				def batchReportDet3=new BatchReportDet(batch:batch2,reportParams:reportparam2,value:"20").save(failOnError: true,flush: true)
				def batchReportDet100=new  BatchReportDet (batch:batch1,reportParams:reportparam100,value:"1.1111").save(failOnError: true, flush: true)
				def batchReportDet101=new  BatchReportDet (batch:batch1,reportParams:reportparam101).save(failOnError: true, flush: true)
				def batchReportDet102=new  BatchReportDet (batch:batch1,reportParams:reportparam102).save(failOnError: true, flush: true)
				// def batchReportDet103=new  BatchReportDet (batch:batch1,reportParams:reportparam103).save(failOnError: true, flush: true)

				def itemRoute1=new ItemRoute(item:item1,sequence:1,operation:operation1,workstation:workstation1).save(failOnError: true, flush: true)
				def itemRoute2=new ItemRoute(item:item1,sequence:2,operation:operation1,workstation:workstation1).save(failOnError: true, flush: true)
				def itemRoute3=new ItemRoute(item:item1,sequence:3,operation:operation2,workstation:workstation2).save(failOnError: true, flush: true)
				def itemRoute4=new ItemRoute(item:item1,sequence:4,operation:operation2,workstation:workstation1).save(failOnError: true, flush: true)
				
				def batchRoute1=new BatchRoute(batch:batch1,workstation:workstation1,sequence:1,operation:operation1).save(failOnError: true,flush: true)
				def batchRoute2=new BatchRoute(batch:batch1,workstation:workstation2,sequence:2,operation:operation2).save(failOnError: true,flush: true)
				def batchRoute3=new BatchRoute(batch:batch1,workstation:workstation2,sequence:3,operation:operation3).save(failOnError: true,flush: true)
				def batchRoute4=new BatchRoute(batch:batch1,workstation:workstation1,sequence:4,operation:operation4).save(failOnError: true,flush: true)
				def batchRoute5=new BatchRoute(batch:batch2,workstation:workstation2,sequence:2,operation:operation2).save(failOnError: true,flush: true)
				def batchRoute6=new BatchRoute(batch:batch2,workstation:workstation2,sequence:3,operation:operation3).save(failOnError: true,flush: true)
				
				// def itemImage1 = new ItemImage(name:"itemImage1.jpg",item:item1).save(failOnError: true, flush: true)
				// def itemImage2 = new ItemImage(name:"itemImage2.jpg",item:item1).save(failOnError: true, flush: true)
				def cutstomer1 = new Customer(name:"cutstomer1",title:"A先生").save(failOnError: true, flush: true)
				def cutstomer2 = new Customer(name:"cutstomer2",title:"B小姐").save(failOnError: true, flush: true)
				// def order=new CustomerOrder(name:'order1').save(failOnError: true, flush: true)
    			def user01 = new User(username: 'user01', password: 'user01', enabled: true).save(failOnError: true, flush: true)
				def user02 = new User(username: 'user02', password: 'user02', enabled: true).save(failOnError: true, flush: true)
				def user03 = new User(username: 'user03', password: 'user03', enabled: true).save(failOnError: true, flush: true)
				def user04 = new User(username: 'user04', password: 'user04', enabled: true).save(failOnError: true, flush: true)
    	}
			


		}
  }
  def destroy = {
  }
}
