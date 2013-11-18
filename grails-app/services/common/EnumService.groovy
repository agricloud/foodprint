package common

import grails.converters.JSON

class EnumService {

	def messageSource

	def values(enumClass){//ex :foodprint.Country

		def enumClassJson =JSON.parse((enumClass.values() as JSON).toString())
		Object[] obj
        enumClassJson.each{
            it.title = messageSource.getMessage('country.'+it.name+'.label',obj, Locale.getDefault())
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