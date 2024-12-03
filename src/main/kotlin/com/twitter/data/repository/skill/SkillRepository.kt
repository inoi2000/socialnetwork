package com.twitter.data.repository.skill

import com.twitter.data.models.Skill

interface SkillRepository {

    suspend fun getSkills(): List<Skill>
}