#include "enemy.h"

enemy::enemy(float speed) {
	enemy_dx = -1*speed;
	en_width = 50.0f;
	en_height = 50.0f;
	enemybox.setSize(sf::Vector2f(en_width, en_height));
	enemybox.setOrigin(en_width / 2, en_height / 2);
	etexture.loadFromFile("hrw.jpg");
	enemybox.setTexture(&etexture);
	//enemybox.setFillColor(sf::Color::Blue);
	float random = (std::rand() % 50) * (512.0f / 51.0f);
	enemybox.setPosition(1050.0f, random);
	pointcounted = false;
}

void enemy::enemymove()
{
	enemybox.move(enemy_dx, 0.0f);
}

void enemy::enemycheckbounds()
{
	if (enemybox.getPosition().x < -10)
	{
		std::cout << "OOB" << std::endl;
		float temp = (std::rand() % 50) * (512.0f / 51.0f);
		enemybox.setPosition(1020.0f, temp);
		pointcounted = false;
	}
}

void enemy::resetenemypos()
{
	float temp = std::rand() % 6 * (904.0f / 7.0f);
	enemybox.setPosition(sf::Vector2f(1050.0f, temp));
	pointcounted = false;
}

bool enemy::enemyhit(player player)
{
	if (enemybox.getGlobalBounds().intersects(player.playerbox.getGlobalBounds()))
		return true;
	return false;
}

void enemy::drawenemy(sf::RenderWindow & window)
{
	window.draw(enemybox);
}

void enemy::enemypoint(player &player)
{
	if (enemybox.getPosition().x < player.playerbox.getPosition().x && !pointcounted)
	{
		player.points+=player.multiplier;
		std::cout << "Points: " << player.points << std::endl;
		pointcounted = true;
	}
}

