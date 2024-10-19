package ru.iuribabalin.model.soap;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "EmployeeSearchResponse", namespace = "http://app.iuribabalin.ru/")
@XmlType(name = "EmployeeSearchResponseType", namespace = "http://app.iuribabalin.ru/")
public class EmployeeSearchResponse {

    private List<SearchEmployeesResponseModel> responseModelList;

}
