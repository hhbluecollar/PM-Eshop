package edu.miu.eshop.eshopadmin.domain;

// AA

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Set;

@Document
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Requirement {
	@Id
	private String id;
	private String vendorId;
	private String subject;
	private LocalDate createdDate;
	private LocalDate lastModifiedDate;
	private LocalDate dueDate;
	private LocalDate endedDate;
	private RequirementStatus status;
	private String description;
	private String engineer;
	private String comments;
}
