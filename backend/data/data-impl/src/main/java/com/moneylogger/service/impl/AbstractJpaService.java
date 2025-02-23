package com.moneylogger.service.impl;

import com.moneylogger.model.Identifiable;
import com.moneylogger.service.api.BaseEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class AbstractJpaService<T extends Identifiable> implements BaseEntityService<T> {
    abstract protected JpaRepository<T, Long> getRepository();

    @Override
    public Optional<T> findById(Long id) {
        log.trace("findById(id = {})", id); // todo create annotation for logging input and output
        return getRepository().findById(id);
    }

    //    @Transactional
    @Override
    public void deleteById(Long id) {
        getRepository().deleteById(id);
    }

    @Override
    public void deleteByIdOnlyIfExists(Long id) throws IllegalStateException {
        if (!getRepository().existsById(id)) {
            throw new IllegalStateException("Can not delete. Object with id = " + id + " does not exist");
        }
        getRepository().deleteById(id);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public <S extends T> S create(S entity) {
        Long id = entity.getId();
        boolean exists = id != null && getRepository().existsById(id);
        if (exists) {
            throw new IllegalStateException("Can not create. Object with id = " + id + " already exist");
        }
        return getRepository().save(entity);
    }

    @Override
    public <S extends T> S update(S newEntity) {
        Long id = newEntity.getId();
        if (!getRepository().existsById(id)) {
            throw new IllegalStateException("Can not update. Object with id = " + id + " does not exist");
        }
        return internalUpdateExcludingSomeFields(newEntity);
    }

    protected <S extends T> S internalUpdateExcludingSomeFields(S newEntity) {
        return getRepository().save(newEntity);
    }

}
