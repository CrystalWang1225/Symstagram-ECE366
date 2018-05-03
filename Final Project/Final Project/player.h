#pragma once
#include <SFML\Graphics.hpp>
#include "Health.h"
#include "ground.h"
#include "menu.h"
#include "enemy.h"
#include "iostream"
class enemy;

class player {
private:
	float playerwidth, playerheight;
	float player_dx, player_dy;
	float max_dx, max_dy;
	const float player_a = 80.0f;
	sf::Texture playertexture;
	float maxhealth;
	float iframe;
	bool hit;
	int maxjump, jumpdone;
	float jumpairtime, minjumpairtime;
public:	
	int resetcounter;
	int points;
	sf::RectangleShape playerbox;
	float health;
	int multiplier;
public:
	player(float length, float height);
	void player_redraw(float length, float height, ground ground);
	void player_update(float dt, bool groundcollide, ground ground);
	void minushealthPSEUDOCODE(bool enemyhit, float dt);
	void drawplayer(sf::RenderWindow& window);
	void player_reset(ground ground);
	void resetpoints();
	void score(enemy enemy);
	bool is_hit();
	friend class health;
	friend class enemy;
	friend class menu;

};