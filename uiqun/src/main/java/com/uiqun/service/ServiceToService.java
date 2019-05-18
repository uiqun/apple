package com.uiqun.service;

import com.uiqun.model.Service;
import com.uiqun.utils.Pager;

public interface ServiceToService {
    boolean insertService(Service service);
    Pager<Service> queryService(Pager<Service> pager);
}
