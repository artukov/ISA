package isa.project.pharmacyapp.service.bean_factory_statistics;

import isa.project.pharmacyapp.model.TimeSpam;
import isa.project.pharmacyapp.strategy_pattern.StatisticsStrategy;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeanFactoryDynamicService {
    private static final String SERVICE_NAME_SUFFIX = "Statistics";
    private final BeanFactory beanFactory;

    @Autowired
    public BeanFactoryDynamicService(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public StatisticsStrategy getStrategyStatistics(TimeSpam timeSpam){
        StatisticsStrategy strategy = beanFactory.getBean(this.getStrategyName(timeSpam),StatisticsStrategy.class);
        return strategy;
    }

    private String getStrategyName(TimeSpam timeSpam) {
        return timeSpam + SERVICE_NAME_SUFFIX;
    }
}
