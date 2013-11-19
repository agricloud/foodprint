package common

import grails.converters.JSON

class EnumService {

	def messageSource

	def values(enumClass){//ex :foodprint.Country

		def enumClassAry = enumClass.values()
		def result = []
		Object[] obj
        enumClassAry.each{
        	def enumClassJson = [:]
        	enumClassJson.title = messageSource.getMessage('country.'+it+'.label',obj, Locale.getDefault())
        	enumClassJson.name = it.name()

        	result << enumClassJson
        }

        result
	}

	def name(enumInstance){//ex: Country country
		
		def enumInstanceJson =[:]
		Object[] obj
		enumInstanceJson.name = enumInstance.name()
		enumInstanceJson.title = messageSource.getMessage('country.'+enumInstance.name()+'.label',obj, Locale.getDefault())

		enumInstanceJson
	}
}