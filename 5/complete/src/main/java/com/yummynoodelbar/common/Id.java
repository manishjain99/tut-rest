package com.yummynoodelbar.common;

import org.apache.commons.lang.ObjectUtils;

public abstract class Id<T extends Number> {
	T t;

	public Id(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}

	public String toString() {
		return t.toString();
	}

	public boolean equals(Object o) {
		if (o == null) return false;
		if(!(o instanceof Id)) return false;
		
		return ObjectUtils.equals(((Id<T>)o).get(), this.get());
	}

	public int hashCode() {
		return t.hashCode();
	}

}
