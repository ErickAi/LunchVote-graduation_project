package com.example.util;


import com.example.HasId;
import com.example.domain.AbstractBaseEntity;
import com.example.util.exception.NotFoundException;

public class ValidationUtil {

    public static final String NOT_FOUND_WITH = "Not found entity with ";
    public static final String NOT_FOUND_WITH_ID = "Not found entity with id= ";

    private ValidationUtil() {
    }

    public static <T extends AbstractBaseEntity> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException(msg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
//      http://stackoverflow.com/a/32728226/548473
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalArgumentException(bean + " must be with id=" + id);
        }
    }

    public static void assureIdConsistent(HasId bean, HasId beanFromDB) {
        if (beanFromDB == null) {
            return;
        }
        if (bean.isNew()) {
            bean.setId(beanFromDB.getId());
        } else if (!bean.getId().equals(beanFromDB.getId())) {
            throw new IllegalArgumentException(bean + " must be with id=" + beanFromDB.getId());
        }
    }

}