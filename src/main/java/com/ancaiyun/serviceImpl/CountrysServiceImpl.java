package com.ancaiyun.serviceImpl;

import com.ancaiyun.mapper.CountrysMapper;
import com.ancaiyun.service.CountrysService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CountrysServiceImpl extends BaseServiceImpl<CountrysMapper> implements CountrysService {

	
}
