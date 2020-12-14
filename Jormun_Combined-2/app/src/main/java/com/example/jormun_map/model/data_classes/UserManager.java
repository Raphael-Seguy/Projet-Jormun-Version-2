package com.example.jormun_map.model.data_classes;

public class UserManager {
    Point pntUserPos;
    Bound bndView;

    private UserManager() {
        setPntUserPos(new Point());
        setBndView(new Bound());
    }

    private static class UserHolder {
        private static final UserManager usrmgrInstance = new UserManager();
    }

    public static UserManager getInstance() {
        return UserHolder.usrmgrInstance;
    }

    public Point getPntUserPos() {
        return pntUserPos;
    }

    public Bound getBndView() {
        return bndView;
    }

    public void setPntUserPos(Point pntUserPos) {
        this.pntUserPos = pntUserPos;
    }

    public void setBndView(Bound bndView) {
        this.bndView = bndView;
    }
}
