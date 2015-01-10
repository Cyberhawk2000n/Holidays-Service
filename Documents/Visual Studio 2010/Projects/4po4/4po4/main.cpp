#include <glut.h>
#include <math.h>
#include <stdlib.h>
#include <time.h>
#include <stdio.h>
#define PI 3.1415926535898
typedef struct
{
	int x;
	int y;
	int colour;
} field_t;
field_t field[25];
int win = 0;
int light = 0;
field_t cursor;
int width, height;
void init(void)
{
	//Выбрать фоновый (очищающий) цвет
	glClearColor(1.0, 0.65, 0.56, 0.0);
	//Установить проекцию
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(0.0, 1.0, 0.0, 1.0, -1.0, 1.0);
}
void display(void)
{
	int i, j, circle_points;
	double angle;
	//Очистить экран glClear(GL_COLOR_BUFFER_BIT);
	glClear(GL_COLOR_BUFFER_BIT);
	//Нарисовать белый полигон (квадрат) с углами //в (0.25, 0.25, 0.0) и (0.75,
	//0.75, 0.0)
	
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
	for (i = 0; i < 25; i++)
	{
		if (field[i].colour != 0 && field[i].colour != 5)
		{
			if (field[i].colour == 1)
				glColor3f(0.0, 0.0, 1.0);
			if (field[i].colour == 2)
				glColor3f(0.0, 1.0, 0.0);
			if (field[i].colour == 3)
				glColor3f(1.0, 1.0, 0.0);
			if (field[i].colour == 4)
				glColor3f(1.0, 0.0, 0.0);
			glBegin(GL_POLYGON);
			for(j=0; j<circle_points; j++)
			{
				angle = 2*PI*j/circle_points;
				glVertex2f(field[i].x * 0.2 - 0.1 + cos(angle) / 11, field[i].y * 0.2 - 0.1 + sin(angle) / 11);
			}
			glEnd(); 
		}
	}
	if (light == 1)
	{
		glColor3f(1.0, 0.0, 0.0);
		glBegin(GL_LINE_LOOP);
		glVertex2f((cursor.x - 1) * 0.2, (cursor.y - 1) * 0.2);
		glVertex2f((cursor.x - 1) * 0.2 + 0.2, (cursor.y - 1) * 0.2);
		glVertex2f((cursor.x - 1) * 0.2 + 0.2, (cursor.y - 1) * 0.2 + 0.2);
		glVertex2f((cursor.x - 1) * 0.2, (cursor.y - 1) * 0.2 + 0.2);
		glEnd();
	}
	else
	{
		glColor3f(0.3, 0.5, 0.8);
		glBegin(GL_LINE_LOOP);
		glVertex2f((cursor.x - 1) * 0.2, (cursor.y - 1) * 0.2);
		glVertex2f((cursor.x - 1) * 0.2 + 0.2, (cursor.y - 1) * 0.2);
		glVertex2f((cursor.x - 1) * 0.2 + 0.2, (cursor.y - 1) * 0.2 + 0.2);
		glVertex2f((cursor.x - 1) * 0.2, (cursor.y - 1) * 0.2 + 0.2);
		glEnd();
	}
	//Не ждем. Начинаем выполнять буферизованные
	//команды OpenGL
	glFlush();
}
//Установить начальные характеристики окна,
//открыть окно с заголовком «4 по 4».
//Зарегистрировать дисплейную функцию обратного вызова
//Войти в главный цикл
void initField()
{
	int i;
	//field of game
	for (i = 0; i < 4; i++)
	{
		field[i].colour = 1; //blue
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
		field[i].colour = 2; //green
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
		field[i].colour = 3; //yellow
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
		field[i].colour = 4; //red
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
		field[i].colour = 5; //wall
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
		field[i].colour = 0; //NULL
	}
	field[21].x = 3;
	field[21].y = 5;
	field[22].x = 3;
	field[22].y = 5;
	field[23].x = 3;
	field[23].y = 5;
	field[24].x = 3;
	field[24].y = 5;
	cursor = field[0];
}
void randField(int num)
{
	int i, temp, field1, field2, pad1, pad2;
	srand(time(NULL));
	for (i = 0; i < num; i++)
	{
		pad1 = rand()%4;
		pad2 = rand()%4;
		pad1 *= 4;
		pad2 *= 4;
		while (pad1 == pad2)
		{
			pad2 = rand()%4;
			pad2 *= 4;
		}
		field1 = pad1 + rand()%4;
		field2 = pad2 + rand()%4;
		temp = field[field1].colour;
		field[field1].colour = field[field2].colour;
		field[field2].colour = temp;
	}
	win = 0;
	light = 0;
}
void processNormalKeys(unsigned char key, int x, int y) {
	if (key == 27)
		exit(0);
}
void processSpecialKeys(int key, int x, int y) {
	switch(key) {
		case GLUT_KEY_LEFT :
		
		break;

	}
}
void mouseButton(int button, int state, int x, int y) {
 
	// только при начале движения, если нажата левая кнопка
	if (button == GLUT_LEFT_BUTTON) {
 
		// когда кнопка отпущена
		if (state == GLUT_DOWN) {
			printf(" %d - %d, %d, %d.", x, y, width, height);
			if (light == 0)
				light = 1;
			else light = 0;
			display();
		}
	}
}
void windowSize(int w, int h)
{
	width = w;
	height = h;
	display();
}
int main(int argc, char **argv)
{
	//int i;
	initField();
	randField(20);
	glutInit(&argc,argv);
	glutInitDisplayMode(GLUT_DEPTH | GLUT_RGB);
	glutInitWindowSize(250, 250);
	glutInitWindowPosition(200, 150);
	glutCreateWindow("4 по 4");
	init();
	glutKeyboardFunc(processNormalKeys);
	glutSpecialFunc(processSpecialKeys);
	glutMouseFunc(mouseButton);
	glutDisplayFunc(display);
	glutReshapeFunc(windowSize);
	glutMainLoop();
	return 0;
}