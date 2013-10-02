package foodprint

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
class ReportParamsController {

    static allowedMethods = [create: "POST",update: "PUT",  delete: "DELETE"]



    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def report_id=params.reportid
        //print report_id
        def reportob=Report.findById(report_id,params);
        //print reportob
        def resultList= ReportParams.findAllByReport(reportob)

        [reportParamsInstanceList: resultList, reportParamsInstanceTotal: ReportParams.count()]
    }
    def index(Integer max) {
        render (contentType: 'text/json') {
            list(max)        
        }
        
    }

    @Transactional
    def create(){

        def reportParamsInstance=new ReportParams(params)
        
        render (contentType: 'text/json') {
            domainService.save(reportParamsInstance)
        }
    }

    @Transactional
    def update(ReportParams reportParamsInstance){

        render (contentType: 'text/json') {
            domainService.save(reportParamsInstance)
        }         
    }


    @Transactional
    def delete(ReportParams reportParamsInstance){
        
        render (contentType: 'text/json') {
            domainService.delete(reportParamsInstance)
        }
    }
}
