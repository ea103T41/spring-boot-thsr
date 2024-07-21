package com.euphy.learn.repository;

import com.euphy.learn.model.LatestVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LatestVersionRepository extends JpaRepository<LatestVersion, Integer> {

    LatestVersion findByVersionType(String versionType);
}
