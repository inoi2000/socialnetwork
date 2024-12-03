package com.twitter.service

import com.twitter.data.models.Skill
import com.twitter.data.repository.skill.SkillRepository

class SkillService(
    private val repository: SkillRepository
) {

    suspend fun getSkills(): List<Skill> {
        return repository.getSkills()
    }
}