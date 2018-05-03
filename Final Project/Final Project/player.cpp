#include <SFML\Graphics.hpp>
#include "player.h"
#include "enemy.h"
#include <iostream>


player::player(float length, float height) {
	resetcounter = 1;
	multiplier = 1;
	points = 0;
	playerwidth = length;
	playerheight = height;
	player_dx = 0.0f;
	player_dy = 0.0f;
	max_dx = 0.0f;//unused
	max_dy = -29.5f;
	playertexture.loadFromFile("cn.jpg");
	playerbox.setSize(sf::Vector2f(playerwidth, playerheight));
	playerbox.setTexture(&playertexture);
	playerbox.setOrigin(playerwidth / 2, playerheight / 2);
	playerbox.setPosition(150.0f, 437.0f);
	//playerbox.setFillColor(sf::Color::Green);
	health = 10.0f;
	maxhealth = health;
	hit = false;
	iframe = 0.0f;
	maxjump = 4;
	jumpdone = 0;
	jumpairtime = 0.0f;
	minjumpairtime = 0.17f;
}

void player::player_redraw(float length, float height, ground ground) {
	playerbox.setSize(sf::Vector2f(length, height));
	playerbox.setOrigin(length / 2, height / 2);
	playerwidth = length;
	playerheight = height;
}

void player::player_update(float dt, bool groundcollide, ground ground) {
	if (groundcollide)
	{
		std::cout << "colliding" << std::endl;
		player_dy = 0.0f;
		player_dx = 0.0f;
		//std::cout << player_dy << std::endl;
		float temp1 = playerbox.getPosition().x;
		float temp2 = 512.0f - ground.groundheight / 2 - playerheight / 2 + 1.0f;
		playerbox.setPosition(temp1, temp2);
		jumpdone = 0;
		jumpairtime = 0;
	}


	//AERIAL MOVEMENT
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Key::Space))
	{
		if (groundcollide)
		{
			if (jumpdone < maxjump)
			{
				std::cout << "attempting to jump" << std::endl;
				player_dy = -28.0f;
				jumpdone++;
			}
		}
		else
		{
			std::cout << "air time" << jumpairtime << std::endl;
			if (jumpdone < maxjump && jumpairtime > minjumpairtime)
			{
				player_dy = -23.0f;
				if (player_dy < max_dy)
				{
					std::cout << "Dy capped" << std::endl;
					player_dy = max_dy;
				}
				jumpairtime = 0.0f;
				jumpdone++;
				std::cout << "jumping again" << std::endl;
			}
		}
		std::cout << "Jumps left" << maxjump - jumpdone << std::endl;
	}

	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Key::S) && !groundcollide)
	{
		player_dy += 1.75f;
	}

	if (!groundcollide)
	{
		jumpairtime += dt;

		//std::cout << "gravity" << std::endl;
		//std::cout << "CURRENT VELOCITY" << player_dy << std::endl;

		player_dy = player_dy + player_a * dt;
		//std::cout << "NOW VELOCITY" << player_dy << std::endl;
	}


	playerbox.move(player_dx, player_dy);

	//GROUND MOVEMENT
	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Key::A))
	{
		if (playerbox.getPosition().x >= 25.0f)
			if (sf::Keyboard::isKeyPressed(sf::Keyboard::Key::LShift))
			{
				playerbox.move(-20.0f, 0.0f);
			}
			else
				playerbox.move(-12.5f, 0.0f);
	}

	if (sf::Keyboard::isKeyPressed(sf::Keyboard::Key::D))
	{
		if (playerbox.getPosition().x <= 1024)
			if (sf::Keyboard::isKeyPressed(sf::Keyboard::Key::LShift))
			{
				playerbox.move(20.0f, 0.0f);
				std::cout << "Running" << std::endl;
			}
			else
				playerbox.move(12.5f, 0.0f);
	}


	//LIMIT MOVEMENT
	if (playerbox.getPosition().x > 1024)
	{
		player_dx = 0;
		playerbox.setPosition(1023.0f, playerbox.getPosition().y);
	}
	else if (playerbox.getPosition().x < 0)
	{
		player_dx = 0;
		playerbox.setPosition(1.0f, playerbox.getPosition().y);
	}
	if (playerbox.getPosition().y < 0)	//when player goes above
	{
		playerbox.setPosition(playerbox.getPosition().x, 0);
		player_dy = 0;
	}
}


//CODE BELOW IS PSEUDOCODE BUT PART CAN BE USED FOR MINUS HEALTH
//MINUSHEALTH FUNC CAN TAKE BOOL OF HIT
void player::minushealthPSEUDOCODE(bool enemyhit, float dt) {
	//std::cout << "health: " << health << std::endl;
	if (enemyhit && !hit)
		//replace if condition with BOOL HIT
	{
		health--;
		std::cout << "minus health" << std::endl << health << "left" << std::endl;
		hit = true;
	}

	if (hit)
	{
		iframe += dt;
		std::cout << "IFRAME: " << iframe << std::endl;
		if (iframe >= (60 * dt))
		{
			std::cout << "cond fulfilled" << std::endl;
			hit = false;
			iframe = 0.0f;
		}
	}
}

void player::drawplayer(sf::RenderWindow & window)
{
	window.draw(playerbox);
}

void player::player_reset(ground ground)
{
	player_dx = 0.0f;
	player_dy = 0.0f;
	float temp = 512.0f - ground.groundheight / 2 - playerheight / 2 + 1.0f;
	playerbox.setPosition(150.0f, temp);
	health = maxhealth;
	hit = false;
	iframe = 0.0f;
	jumpdone = 0;
	jumpairtime = 0.0f;
}

void player::resetpoints() {
	points = 0;
	multiplier = 1;
}

bool player::is_hit() {
	return hit;
}
