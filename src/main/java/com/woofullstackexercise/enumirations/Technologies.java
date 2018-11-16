package com.woofullstackexercise.enumirations;

public enum Technologies {
	JAVA, PYTON, RYBY, LINUX, REST, OOP, HIBERNATE, MAVEN, GIT, MYSQL, SQL, ANGULAR;

	public static Technologies getRandom() {
		Technologies res = null;
		res = Technologies.values()[(int) (Math.random() * Technologies.values().length)];
		return res;
	}
}
