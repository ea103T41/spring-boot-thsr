package com.euphy.learn.service;

import com.euphy.learn.model.LatestVersion;
import com.euphy.learn.repository.LatestVersionRepository;
import com.euphy.learn.tdx.dto.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LatestVersionService {

    private final LatestVersionRepository latestVersionRepository;

    @Autowired
    public LatestVersionService(LatestVersionRepository latestVersionRepository) {
        this.latestVersionRepository = latestVersionRepository;
    }

    public boolean updateLatestVersion(String versionType, Version version) {
        LatestVersion latestVersion = latestVersionRepository.findByVersionType(versionType);

        boolean isNew = false;
        if (latestVersion == null) {
            latestVersion = new LatestVersion();
            latestVersion.setVersionType(versionType);
            latestVersion.setVersionId(version.getVersionId());
            latestVersion.setUpdateTime(version.getUpdateTime());
            latestVersionRepository.save(latestVersion);
            isNew = true;
        }
        else if (!latestVersion.getVersionId().equals(version.getVersionId())) {
            latestVersion.setVersionId(version.getVersionId());
            latestVersion.setUpdateTime(version.getUpdateTime());
            latestVersionRepository.save(latestVersion);
            isNew = true;
        }
        return isNew;
    }

}
