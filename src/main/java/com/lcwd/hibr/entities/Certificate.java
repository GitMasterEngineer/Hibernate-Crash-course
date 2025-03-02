package com.lcwd.hibr.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="student_certificate")
public class Certificate {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long certificateId;
		private String title;
		private String about;
		private String link;
		
		@ManyToOne
		@JoinColumn(name="student_id")
		private Student student;
}
