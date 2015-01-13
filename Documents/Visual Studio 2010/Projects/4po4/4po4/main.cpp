#include <glut.h>
#include <math.h>
#include <stdlib.h>
#include <time.h>
#include <stdio.h>
#define PI 3.1415926535898
#define FIELD_NUM 25 // Number of positions on the game field
#define RAND_NUM 15 // Number of mixing
void initField();
void randField();
typedef struct
{
	int x; // Coordinate X
	int y; // Coordinate Y
	int colour; // Colour of position (0, if nothing; 5, if wall)
} field_t;
field_t field[FIELD_NUM];
int win = 0; // As boolean (used for winner notification)
int light = 0; // As boolean (used for cursor drawing)
field_t cursor;
int width, height; // Global variables for window size
void init(void)
{
	// Choose background colour
	glClearColor(1.0, 0.65, 0.56, 0.0);
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(0.0, 1.0, 0.0, 1.0);
}
void switchColour(int colour, int variant) // For choise of position colour
{
	float dark = 0.75f;
	float light = 1.0f;
	if (variant == 0) // Light colour
		switch (colour)
		{
			case 1:
			glColor3f(0.0, 0.0, light);
			break;
			case 2:
			glColor3f(0.0, light, 0.0);
			break;
			case 3:
			glColor3f(light, light, 0.0);
			break;
			default:
			glColor3f(light, 0.0, 0.0);
			break;
		}
	else // Dark colour
		switch (colour)
		{
			case 1:
			glColor3f(0.0, 0.0, dark);
			break;
			case 2:
			glColor3f(0.0, dark, 0.0);
			break;
			case 3:
			glColor3f(dark, dark, 0.0);
			break;
			default:
			glColor3f(dark, 0.0, 0.0);
			break;
		}
}
void drawString(float x, float y, void *font, const char* string) // For drawing of char array with translation
{
	glPushMatrix();
	glRasterPos2f(x, y);
	while (*string)
		glutBitmapCharacter(font, *string++);
	glPopMatrix();
}
void display(void)
{
	int i, j, circle_points;
	double angle;
	//Clear screen with glClear(GL_COLOR_BUFFER_BIT);
	glClear(GL_COLOR_BUFFER_BIT);
	//Draw polygons (squares)
	glColor3f(0.0, 0.0, 0.0);
	glBegin(GL_POLYGON);
	glVertex2f(0.4, 0.0);
	glVertex2f(0.4, 0.2);
	glVertex2f(0.6, 0.2);
	glVertex2f(0.6, 0.0);
	glEnd();
	glBegin(GL_POLYGON);
	glVertex2f(0.8, 0.4);
	glVertex2f(1.0, 0.4);
	glVertex2f(1.0, 0.6);
	glVertex2f(0.8, 0.6);
	glEnd();
	glBegin(GL_POLYGON);
	glVertex2f(0.0, 0.4);
	glVertex2f(0.2, 0.4);
	glVertex2f(0.2, 0.6);
	glVertex2f(0.0, 0.6);
	glEnd();
	glBegin(GL_POLYGON);
	glVertex2f(0.4, 1.0);
	glVertex2f(0.6, 1.0);
	glVertex2f(0.6, 0.8);
	glVertex2f(0.4, 0.8);
	glEnd();
	glBegin(GL_POLYGON);
	glVertex2f(0.4, 0.4);
	glVertex2f(0.4, 0.6);
	glVertex2f(0.6, 0.6);
	glVertex2f(0.6, 0.4);
	glEnd();
	glColor3f(1.0, 0.4, 0.6);
	glColor3f(1.0, 0.0, 1.0);
	glBegin(GL_POLYGON);
	glVertex2f(0.41, 0.01);
	glVertex2f(0.41, 0.19);
	glVertex2f(0.59, 0.19);
	glVertex2f(0.59, 0.01);
	glEnd();
	glBegin(GL_POLYGON);
	glVertex2f(0.81, 0.41);
	glVertex2f(0.99, 0.41);
	glVertex2f(0.99, 0.59);
	glVertex2f(0.81, 0.59);
	glEnd();
	glBegin(GL_POLYGON);
	glVertex2f(0.01, 0.41);
	glVertex2f(0.19, 0.41);
	glVertex2f(0.19, 0.59);
	glVertex2f(0.01, 0.59);
	glEnd();
	glBegin(GL_POLYGON);
	glVertex2f(0.41, 0.99);
	glVertex2f(0.59, 0.99);
	glVertex2f(0.59, 0.81);
	glVertex2f(0.41, 0.81);
	glEnd();
	glBegin(GL_POLYGON);
	glVertex2f(0.41, 0.41);
	glVertex2f(0.41, 0.59);
	glVertex2f(0.59, 0.59);
	glVertex2f(0.59, 0.41);
	glEnd();
	circle_points = 100;
	for (i = 0; i < FIELD_NUM; i++) // Cycle for drawing circles on the field
	{
		if (field[i].colour != 0 && field[i].colour != 5)
		{
			switchColour(field[i].colour, 0); // Light circle
			glBegin(GL_POLYGON);
			for(j=0; j<circle_points; j++)
			{
				angle = 2*PI*j/circle_points;
				glVertex2f(field[i].x * 0.2 - 0.1 + cos(angle) / 11, field[i].y * 0.2 - 0.1 + sin(angle) / 11);
			}
			glEnd();
			switchColour(field[i].colour, 1); // Dark circle
			glBegin(GL_POLYGON);
			for(j=0; j<circle_points; j++)
			{
				angle = 2*PI*j/circle_points;
				glVertex2f(field[i].x * 0.2 - 0.1 + cos(angle) / 18, field[i].y * 0.2 - 0.1 + sin(angle) / 18);
			}
			glEnd(); 
		}
	}
	if (light == 1) // Draw cursor
	{
		glColor3f(1.0, 0.0, 0.0);
		glBegin(GL_POLYGON);
		glVertex2f((cursor.x - 1) * 0.2, (cursor.y - 1) * 0.2);
		glVertex2f((cursor.x - 1) * 0.2 + 0.03, (cursor.y - 1) * 0.2);
		glVertex2f((cursor.x - 1) * 0.2, (cursor.y - 1) * 0.2 + 0.03);
		glEnd();
		glBegin(GL_POLYGON);
		glVertex2f((cursor.x - 1) * 0.2 + 0.17, (cursor.y - 1) * 0.2);
		glVertex2f((cursor.x - 1) * 0.2 + 0.2, (cursor.y - 1) * 0.2);
		glVertex2f((cursor.x - 1) * 0.2 + 0.2, (cursor.y - 1) * 0.2 + 0.03);
		glEnd();
		glBegin(GL_POLYGON);
		glVertex2f((cursor.x - 1) * 0.2 + 0.2, (cursor.y - 1) * 0.2 + 0.17);
		glVertex2f((cursor.x - 1) * 0.2 + 0.2, (cursor.y - 1) * 0.2 + 0.2);
		glVertex2f((cursor.x - 1) * 0.2 + 0.17, (cursor.y - 1) * 0.2 + 0.2);
		glEnd();
		glBegin(GL_POLYGON);
		glVertex2f((cursor.x - 1) * 0.2, (cursor.y - 1) * 0.2 + 0.17);
		glVertex2f((cursor.x - 1) * 0.2, (cursor.y - 1) * 0.2 + 0.2);
		glVertex2f((cursor.x - 1) * 0.2 + 0.03, (cursor.y - 1) * 0.2 + 0.2);
		glEnd();
	}
	if (win == 1) // Draw winner notification
	{
		glColor3f(0.0f, 191.0f/255.0f, 1.0f);
		glBegin(GL_POLYGON);
		glVertex2f(0.15, 0.25);
		glVertex2f(0.85, 0.25);
		glVertex2f(0.85, 0.75);
		glVertex2f(0.15, 0.75);
		glEnd();
		glColor3f(1.0f, 215.0f/255.0f, 0.0f);
		glBegin(GL_POLYGON);
		glVertex2f(0.2, 0.3);
		glVertex2f(0.8, 0.3);
		glVertex2f(0.8, 0.7);
		glVertex2f(0.2, 0.7);
		glEnd();
		glColor3f(1.0, 0.0, 0.0);
		drawString(0.28f, 0.48f, GLUT_BITMAP_TIMES_ROMAN_24, "YOU WIN!");
		initField();
		randField(RAND_NUM);
		win = 0;
	}
	// Output OpenGL picture
	glFlush();
}
void initField() // Initialization of game field (25 positions, there are coordinates and colour for every position)
{
	int i;
	// Field of game
	for (i = 0; i < 4; i++)
	{
		field[i].colour = 1; // Blue
	}
	field[0].x = 1;
	field[0].y = 1;
	field[1].x = 2;
	field[1].y = 1;
	field[2].x = 1;
	field[2].y = 2;
	field[3].x = 2;
	field[3].y = 2;
	for (i = 4; i < 8; i++)
	{
		field[i].colour = 2; // Green
	}
	field[4].x = 4;
	field[4].y = 1;
	field[5].x = 5;
	field[5].y = 1;
	field[6].x = 4;
	field[6].y = 2;
	field[7].x = 5;
	field[7].y = 2;
	for (i = 8; i < 12; i++)
	{
		field[i].colour = 3; // Yellow
	}
	field[8].x = 1;
	field[8].y = 4;
	field[9].x = 2;
	field[9].y = 4;
	field[10].x = 1;
	field[10].y = 5;
	field[11].x = 2;
	field[11].y = 5;
	for (i = 12; i < 16; i++)
	{
		field[i].colour = 4; // Red
	}
	field[12].x = 4;
	field[12].y = 4;
	field[13].x = 5;
	field[13].y = 4;
	field[14].x = 4;
	field[14].y = 5;
	field[15].x = 5;
	field[15].y = 5;
	for (i = 16; i < 21; i++)
	{
		field[i].colour = 5; // Wall
	}
	field[16].x = 3;
	field[16].y = 1;
	field[17].x = 1;
	field[17].y = 3;
	field[18].x = 3;
	field[18].y = 3;
	field[19].x = 5;
	field[19].y = 3;
	field[20].x = 3;
	field[20].y = 5;
	for (i = 21; i < 25; i++)
	{
		field[i].colour = 0; // NULL
	}
	field[21].x = 3;
	field[21].y = 2;
	field[22].x = 2;
	field[22].y = 3;
	field[23].x = 4;
	field[23].y = 3;
	field[24].x = 3;
	field[24].y = 4;
	cursor = field[0];
}
void randField(int num)
{
	int i, temp, field1, field2, pad1, pad2;
	srand(time(NULL));
	for (i = 0; i < num; i++)
	{
		pad1 = rand()%4; // rand()%4 for choise segment
		pad2 = rand()%4;
		pad1 *= 4;
		pad2 *= 4;
		while (pad1 == pad2)
		{
			pad2 = rand()%4;
			pad2 *= 4;
		}
		field1 = pad1 + rand()%4; // Padding + rand()%4 for choice position
		field2 = pad2 + rand()%4;
		temp = field[field1].colour;
		field[field1].colour = field[field2].colour;
		field[field2].colour = temp;
	}
	win = 0;
	light = 0;
}
void anim(int curs, int targ) // Cursor and target indexes (for animation)
{
	double angle, i;
	int k, j, temp, circle_p = 100;
	temp = field[curs].colour;
	field[curs].colour = 0;
	if (field[curs].x == field[targ].x)
	{
		if (field[curs].y < field[targ].y) // When move down
		{
			i = field[curs].y * 0.2;
			while (i < (field[targ].y * 0.2))
			{
				display();
				switchColour(temp, 0);
				glBegin(GL_POLYGON);
				for(j=0; j<circle_p; j++)
				{
					angle = 2*PI*j/circle_p;
					glVertex2f(field[curs].x * 0.2 - 0.1 + cos(angle) / 11, i - 0.1 + sin(angle) / 11);
				}
				glEnd();
				switchColour(temp, 1);
				glBegin(GL_POLYGON);
				for(j=0; j<circle_p; j++)
				{
					angle = 2*PI*j/circle_p;
					glVertex2f(field[curs].x * 0.2 - 0.1 + cos(angle) / 18, i - 0.1 + sin(angle) / 18);
				}
				glEnd(); 
				glFlush();
				i = i + 0.004;
				for (k = 0; k<1000000; k++); // Waiting for time
			}
		}
		else	// When move up
		{
			i = field[curs].y * 0.2;
			while (i > (field[targ].y * 0.2))
			{
				display();
				switchColour(temp, 0);
				glBegin(GL_POLYGON);
				for(j=0; j<circle_p; j++)
				{
					angle = 2*PI*j/circle_p;
					glVertex2f(field[curs].x * 0.2 - 0.1 + cos(angle) / 11, i - 0.1 + sin(angle) / 11);
				}
				glEnd();
				switchColour(temp, 1);
				glBegin(GL_POLYGON);
				for(j=0; j<circle_p; j++)
				{
					angle = 2*PI*j/circle_p;
					glVertex2f(field[curs].x * 0.2 - 0.1 + cos(angle) / 18, i - 0.1 + sin(angle) / 18);
				}
				glEnd(); 
				glFlush();
				i = i - 0.004;
				for (k = 0; k<1000000; k++); // Waiting for time
			}
		}
	}
	if (field[curs].y == field[targ].y)
	{
		if (field[curs].x < field[targ].x) // When move right
		{
			i = field[curs].x * 0.2;
			while (i < (field[targ].x * 0.2))
			{
				display();
				switchColour(temp, 0);
				glBegin(GL_POLYGON);
				for(j=0; j<circle_p; j++)
				{
					angle = 2*PI*j/circle_p;
					glVertex2f(i - 0.1 + cos(angle) / 11, field[curs].y * 0.2 - 0.1 + sin(angle) / 11);
				}
				glEnd();
				switchColour(temp, 1);
				glBegin(GL_POLYGON);
				for(j=0; j<circle_p; j++)
				{
					angle = 2*PI*j/circle_p;
					glVertex2f(i - 0.1 + cos(angle) / 18, field[curs].y * 0.2 - 0.1 + sin(angle) / 18);
				}
				glEnd(); 
				glFlush();
				i = i + 0.004;
				for (k = 0; k<1000000; k++); // Waiting for time
			}
		}
		else	// When move left
		{
			i = field[curs].x * 0.2;
			while (i > (field[targ].x * 0.2))
			{
				display();
				switchColour(temp, 0);
				glBegin(GL_POLYGON);
				for(j=0; j<circle_p; j++)
				{
					angle = 2*PI*j/circle_p;
					glVertex2f(i - 0.1 + cos(angle) / 11, field[curs].y * 0.2 - 0.1 + sin(angle) / 11);
				}
				glEnd();
				switchColour(temp, 1);
				glBegin(GL_POLYGON);
				for(j=0; j<circle_p; j++)
				{
					angle = 2*PI*j/circle_p;
					glVertex2f(i - 0.1 + cos(angle) / 18, field[curs].y * 0.2 - 0.1 + sin(angle) / 18);
				}
				glEnd(); 
				glFlush();
				i = i - 0.004;
				for (k = 0; k<1000000; k++); // Waiting for time
			}
		}
	}
	field[targ].colour = temp;
}
void processNormalKeys(unsigned char key, int x, int y) {
	if (key == 27) // Exit from game by pushing "Esc" button
		exit(0);
	if (key == 'r' || key == 'R')
	{
		// New game
		initField();
		randField(RAND_NUM);
		display();
	}
}
void mouseButton(int button, int state, int x, int y) {
	int field1 = 0, field2 = 0;
	int i, xpos, ypos; // xpos, ypos - positions on the field (1..5)
	if (button == GLUT_RIGHT_BUTTON) {
		if (state == GLUT_DOWN) {
			// New game
			initField();
			randField(RAND_NUM);
			display();
		}
	}
	// If left button is pushed
	if (button == GLUT_LEFT_BUTTON) {
		// When left button is pushed
		if (state == GLUT_DOWN) {
			xpos = x * 5 / width + 1;
			ypos = (height - y) * 5 / height + 1;
			if (light == 0) // When the position is not signed
			{
				for (i = 0; i < FIELD_NUM; i++)
				{
					if (field[i].x == xpos && field[i].y == ypos)
					{
						if (field[i].colour != 0 && field[i].colour != 5)
						{
							// Pick out the cursor
							cursor = field[i];
						}
					}
				}
				// Draw cursor
				light = 1;
			}
			else 
			{
				// Move to the position if cursor is neighboring position
				if (((xpos == (cursor.x + 1)) && (ypos == cursor.y)) || ((xpos == (cursor.x - 1)) && (ypos == cursor.y)) ||
					((xpos == cursor.x) && (ypos == (cursor.y + 1))) || ((xpos == cursor.x) && (ypos == (cursor.y - 1))))
				{
					for (i = 0; i < FIELD_NUM; i++)
						{
							if ((field[i].x == cursor.x) && (field[i].y == cursor.y))
								{
									field1 = i;
								}
						}
					for (i = 0; i < FIELD_NUM; i++)
					{
						if ((field[i].x == xpos) && (field[i].y == ypos) && (field[i].colour == 0))
						{
							anim(field1, i);
							cursor = field[i];
						}
					}
				}
				// Not draw the cursor
				light = 0;
				// Checking for win
				if ((field[0].colour == field[1].colour) && (field[1].colour == field[2].colour) && (field[2].colour == field[3].colour) &&
					(field[4].colour == field[5].colour) && (field[5].colour == field[6].colour) && (field[6].colour == field[7].colour) &&
					(field[8].colour == field[9].colour) && (field[9].colour == field[10].colour) && (field[10].colour == field[11].colour) &&
					(field[12].colour == field[13].colour) && (field[13].colour == field[14].colour) && (field[14].colour == field[15].colour))
				{
					win = 1;
				}
			}
			display();
		}
	}
}
void windowSize(int w, int h)
{
	width = w;
	height = h;
	// Determine window sizes
	glViewport(0, 0, w, h);
	init();
	display();
}
int main(int argc, char **argv)
{
	// Window parameters
	width = 250;
	height = 250;
	initField(); // Create field positions
	randField(RAND_NUM); // Mix field positions in segments
	glutInit(&argc,argv);
	glutInitDisplayMode(GLUT_RGB);
	// Setup window parameters
	glutInitWindowSize(width, height);
	glutInitWindowPosition(200, 150);
	// Open the window with title «4 on 4»
	glutCreateWindow("4 ïî 4");
	init();
	// Functions for 
	glutKeyboardFunc(processNormalKeys);
	glutMouseFunc(mouseButton);
	glutDisplayFunc(display);
	glutReshapeFunc(windowSize);
	// Come to main cycle
	glutMainLoop();
	return 0;
}