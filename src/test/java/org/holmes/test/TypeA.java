package org.holmes.test;

public final class TypeA {

	private int id;

	public TypeA(int id) {

		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TypeA)) {
			return false;
		}

		TypeA other = (TypeA) obj;
		if (id != other.id) {
			return false;
		}

		return true;
	}

}
