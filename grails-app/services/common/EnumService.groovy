package common

import grails.converters.JSON

class EnumService {

	def messageSource

	def values(enumClass){//ex :foodprint.Country

		def enumClassAry = enumClass.values()
		def enumClassJson = [:]
		Object[] obj
        enumClassAry.each{
        	enumClassJson.title = messageSource.getMessage('country.'+it+'.label',obj, Locale.getDefault())
        	enumClassJson.name = it
        }

        enumClassJson
	}

	def name(enumInstance){//ex: Country country
		
		def enumInstanceJson =[:]
		Object[] obj
		enumInstanceJson.name = enumInstance.name()
		enumInstanceJson.title = messageSource.getMessage('country.'+enumInstance.name()+'.label',obj, Locale.getDefault())

		enumInstanceJson
	}
}