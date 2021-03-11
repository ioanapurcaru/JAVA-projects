package controller;

import dao.AuditDao;
import model.Audit;

public class AuditController {

    private static final class Singleton{
        public static final AuditController INSTANCE = new AuditController();
    }

    private final AuditDao auditDao;

    private AuditController() {
        auditDao = new AuditDao(ConnectionManager.getInstance().getConnection());
    }

    public boolean create(Audit a) {
        return auditDao.addAudit(a);
    }

    public boolean remove(Audit a) {
        return auditDao.delete(a);
    }

}
