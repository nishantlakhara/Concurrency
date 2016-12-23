package com.semaphores;

public class Person {
	private long l;
	private String string;
	private String string2;
	
	public Person(long l, String string, String string2) {
		this.l = l;
		this.string = string;
		this.string2 = string2;
	}
	
	public Person copyPerson() {
		Person person = new Person(this.l , this.string , this.string2);
		return person;
	}
	
	public String toString() {
		return "Person : [" + l+  ", String = "+string +", String2 = " + string2 + "]";
	}
}
