package com.bhrobotics.morcontrol;

public class ThreadTag {
    private int id;

    public ThreadTag(int id) {
	this.id = id;
    }

    public int getId() {
	return id;
    }

    public boolean equals(Object object) {
	if (object == null) {
	    return false;
	} else if (!(object instanceof ThreadTag)) {
	    return false;
	} else {
	    return ((ThreadTag) object).getId() == getId();
	}
    }
}
