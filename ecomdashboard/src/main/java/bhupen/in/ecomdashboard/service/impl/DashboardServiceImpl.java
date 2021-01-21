package bhupen.in.ecomdashboard.service.impl;

import bhupen.in.ecomdashboard.entity.*;
import bhupen.in.ecomdashboard.repositories.*;
import bhupen.in.ecomdashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by BhupendraKumar on 7/11/18.
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private CompanyRevenueRepository companyRevenueRepository;

    @Autowired
    private EmployeeInformationRepository employeeInformationRepository;

    @Autowired
    private OrderCollectionStatusRepository orderCollectionStatusRepository;

    @Autowired
    private OrderRecievedRepository orderRecievedRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;


    @Override
    public HashMap<String, Object> getTodayRevenueDash() {
        HashMap<String, Object> populateCmpnyRev = new HashMap<>();

        List<CompanyRevenue> companyRevenueList = companyRevenueRepository.findAll();

        /**
         * Build Labels and Revenue
         */
        List<String> label = new ArrayList<>();
        List<String> _revenue = new ArrayList<>();
        double totalMargin = 0;
        double totalExpense = 0;
        double totalRevenue = 0;

        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        for (CompanyRevenue companyRevenue : companyRevenueList) {
            label.add(companyRevenue.get_month());
            _revenue.add(String.valueOf(companyRevenue.getRevenue()));
            totalExpense = totalExpense + companyRevenue.getExpense();
            totalMargin = totalMargin + companyRevenue.getMargins();
            totalRevenue = totalRevenue + companyRevenue.getRevenue();

        }
        populateCmpnyRev.put("crLabels", label.toString());
        populateCmpnyRev.put("crRevenue", _revenue.toString());
        populateCmpnyRev.put("totalExpense", currencyFormatter.format(totalExpense));
        populateCmpnyRev.put("totalMargin", currencyFormatter.format(totalMargin));
        populateCmpnyRev.put("totalRevenue", currencyFormatter.format(totalRevenue));


        return populateCmpnyRev;
    }


    @Override
    public HashMap<String, Object> getProductCategories() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();
        HashMap<String, Object> pieChartValues = new HashMap<>();

        List<String> categoryNames = productCategories.stream()
                .map(ProductCategory::getCategoryName)
                .collect(Collectors.toList());

        List<Integer> categoryPercentages =  productCategories.stream()
                .map(ProductCategory::getPercentage)
                .collect(Collectors.toList());

        pieChartValues.put("categoryNames", categoryNames);
        pieChartValues.put("categoryPercentages", categoryPercentages);
        return pieChartValues;
    }

    @Override
    public HashMap<String, Object> getAllOrderRecieved() {
        List<OrderRecieved> ordersReceived = orderRecievedRepository.findAll();
        HashMap<String, Object> areaChartValues = new HashMap<>();
        List<String> dateReceived = ordersReceived.stream()
                .map(OrderRecieved::getDateReceived)
                .collect(Collectors.toList());

        List<Integer> orderReceived = ordersReceived.stream()
                .map(OrderRecieved::getOrderReceived)
                .collect(Collectors.toList());

        areaChartValues.put("datesReceived", dateReceived);
        areaChartValues.put("ordersReceived", orderReceived);
        return areaChartValues;
    }

    @Override
    public List<OrderCollectionStatus> getOrderCollection() {
        return orderCollectionStatusRepository.findAll();
    }

    @Override
    public List<EmployeeInformation> getAllEmployee() {
        return employeeInformationRepository.findAll();
    }

    @Override
    public EmployeeInformation getEmployee(String pk) {
        return employeeInformationRepository.findByPk(pk);
    }

    @Override
    public EmployeeInformation addEmployee(EmployeeInformation employeeInformation) {
        return employeeInformationRepository.save(employeeInformation);
    }

    @Override
    public EmployeeInformation updateEmployee(EmployeeInformation employeeInformation) {
        return employeeInformationRepository.save(employeeInformation);
    }

    @Override
    public void deleteEmployee(EmployeeInformation employeeInformation) {
        employeeInformationRepository.delete(employeeInformation);
    }
}
