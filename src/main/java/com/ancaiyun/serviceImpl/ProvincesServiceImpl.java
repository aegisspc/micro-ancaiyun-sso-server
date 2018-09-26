package com.ancaiyun.serviceImpl;

import com.ancaiyun.mapper.ProvincesMapper;
import com.ancaiyun.service.ProvincesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProvincesServiceImpl extends BaseServiceImpl<ProvincesMapper> implements ProvincesService {
	

}
