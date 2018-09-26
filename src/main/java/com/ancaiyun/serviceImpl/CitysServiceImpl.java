package com.ancaiyun.serviceImpl;

import com.ancaiyun.mapper.CitysMapper;
import com.ancaiyun.service.CitysService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CitysServiceImpl extends BaseServiceImpl<CitysMapper> implements CitysService {
	

}
