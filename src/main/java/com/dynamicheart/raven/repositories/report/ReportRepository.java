package com.dynamicheart.raven.repositories.report;

import com.dynamicheart.raven.model.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dynamicheart on 21/8/2017.
 */
public interface ReportRepository extends JpaRepository<Report, Long> {
}
