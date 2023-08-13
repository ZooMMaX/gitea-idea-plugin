/*
 * Copyright (c) 2023. Leon<leondevlifelog@gmail.com>. All rights reserved.
 * SPDX-License-Identifier: MIT
 */

package com.github.leondevlifelog.gitea.api


import com.github.leondevlifelog.gitea.authentication.accounts.GiteaServerPath
import com.intellij.collaboration.util.resolveRelative
import git4idea.remote.hosting.HostedRepositoryCoordinates
import com.intellij.openapi.util.NlsSafe
import java.net.URI

data class GiteaRepositoryCoordinates(
    override val serverPath: GiteaServerPath, val repositoryPath: GiteaRepositoryPath
) : HostedRepositoryCoordinates {

    fun toUrl(): String {
        return serverPath.toURI().toString() + "/" + repositoryPath
    }

    fun getWebURI(): URI = serverPath.toURI().resolveRelative(repositoryPath.toString())

    @NlsSafe
    override fun toString(): String {
        return "$serverPath/$repositoryPath"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GiteaRepositoryCoordinates) return false

        if (serverPath != other.serverPath) return false
        if (repositoryPath != other.repositoryPath) return false

        return true
    }

    override fun hashCode(): Int {
        var result = serverPath.hashCode()
        result = 31 * result + repositoryPath.hashCode()
        return result
    }
}