/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.service.config;

import org.dozer.CustomConverter;

/**
 *
 * @author charlliz
 */
public class CarStateCustomConverter implements CustomConverter {
  
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class destinationClass, Class sourceClass) {
		if (null == sourceFieldValue)
			return null;
		if (Enum.class.isAssignableFrom(sourceClass)
				&& Enum.class.isAssignableFrom(destinationClass)) {
			return Enum.valueOf((Class<Enum>) destinationClass,
					sourceFieldValue.toString());
		}
		return null;
	}
  } 
