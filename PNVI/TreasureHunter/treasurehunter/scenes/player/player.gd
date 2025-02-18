extends CharacterBody3D

@export var speed: float = 5.0
@export var jump_force: float = 5.0
@export var gravity: float = 9.8
@export var mouse_sensitivity: float = 0.1

var look_angle = 0.0  # Vertical camera angle

@onready var camera = $Camera3D  # Get the camera node

func _ready():
	Input.set_mouse_mode(Input.MOUSE_MODE_CAPTURED)  # Lock and hide the cursor

func _input(event):
	if event is InputEventMouseMotion:
		rotate_y(-event.relative.x * mouse_sensitivity * 0.01)  # Rotate left/right
		look_angle -= event.relative.y * mouse_sensitivity * 0.01  # Look up/down
		look_angle = clamp(look_angle, -1.5, 1.5)  # Limit vertical look
		camera.rotation.x = look_angle

func _physics_process(delta):
	var direction = Vector3.ZERO

	if Input.is_action_pressed("move_forward"):
		direction -= transform.basis.z
	if Input.is_action_pressed("move_backward"):
		direction += transform.basis.z
	if Input.is_action_pressed("move_left"):
		direction -= transform.basis.x
	if Input.is_action_pressed("move_right"):
		direction += transform.basis.x

	direction = direction.normalized()
	velocity.x = direction.x * speed
	velocity.z = direction.z * speed

	if not is_on_floor():
		velocity.y -= gravity * delta

	if is_on_floor() and Input.is_action_just_pressed("jump"):
		velocity.y = jump_force

	move_and_slide()
