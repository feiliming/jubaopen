package com.dsideal.jbp.bt.parts;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

/**
 * 输入数字校验.
 * 
 * @author feilm220
 *
 */
public class NumberValiditor implements IValidator{

	@Override
	public IStatus validate(Object value) {
		if (value instanceof Integer) {
			String s = String.valueOf(value);
			if (s.matches(".*\\d.*")) {
				return ValidationStatus.ok();
			}
		}
		return ValidationStatus.error("输入不合法");
	}

}
