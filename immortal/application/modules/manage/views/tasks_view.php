<!-- Main content -->
<div class="content-wrapper">

<!-- Page header -->
<div class="page-header page-header-default">
	<div class="breadcrumb-line">
		<ul class="breadcrumb">
			<li><a href="<?php echo base_url() ?>"><i class="icon-home2 position-left"></i>Home</a></li>
			<li class="active">Tasks</li>
		</ul>
	</div>
</div>
<!-- /page header -->


<!-- Content area -->
<div class="content">
<!-- Basic responsive configuration -->
<div class="panel panel-flat">
	<div class="panel-body">
		<button style="margin-left: 10px" class="btn btn-success btn-xs" onclick="new_task()"><i
				class="glyphicon glyphicon-plus-sign"></i> New task
		</button>
		<button class="btn btn-default btn-xs" data-action="reload"><i
				class="glyphicon glyphicon-refresh"></i> Reload
		</button>
	</div>

	<table class="table datatable-responsive">
		<thead>
		<tr>
			<th>STT</th>
			<th>Title</th>
			<th>Description</th>
			<th>Date from</th>
            <th>Date to</th>
			<th class="text-center">Actions</th>
		</tr>
		</thead>
		<tbody>
		<?php
			$stt = 1;
			foreach ($tasks_list as $task) {
				echo '<tr>';
				echo '<td>' . $stt . '</td>';
				echo '<td>' . $task->title . '</td>';
				echo '<td>' . $task->description . '</td>';
				echo '<td>' . $task->date_from . '</td>';
				echo '<td>' . $task->date_to . '</td>';
				echo '<td class="text-center" style="width: 200px"><a title="Edit task" onclick="edit_task(' . "'" . $task->id . "'" . ')"><i class="glyphicon glyphicon-pencil"></i></a> &nbsp;
						<a title="Delete task" onclick="delete_task_confirm(' . "'" . $task->id . "'" . ')"><i class="icon-trash"></i></a></td>';
				echo '</tr>';
				$stt++;
			}
		?>
		</tbody>
	</table>
</div>
<!-- /basic responsive configuration -->

<script type="text/javascript">

	var save_method; //for save method string
	var table;

	//Them account moi
	function new_task() {
		save_method = 'add';
		$('#form')[0].reset(); // reset form on modals
		$('.form-group').removeClass('has-error'); // clear error class
		$('.help-block').empty(); // clear error string
		$('#modal_form').modal('show'); // show bootstrap modal
		$('.modal-title').text('New task'); // Set Title to Bootstrap modal title
	}

	function edit_task(id) {
		save_method = 'update';
		$('#form')[0].reset(); // reset form on modals
		$('.form-group').removeClass('has-error'); // clear error class
		$('.help-block').empty(); // clear error string

		//Ajax Load data from ajax
		$.ajax({
			url: "<?php echo site_url('manage/tasks/ajax_edit/')?>/" + id,
			type: "GET",
			dataType: "JSON",
			success: function (data) {

				$('[name="idtask"]').val(data.id);
				$('[name="title"]').val(data.title);
				$('[name="date_from"]').val(data.date_from);
				$('[name="date_to"]').val(data.date_to);
				$('[name="description"]').val(data.description);
				$('#modal_form').modal('show'); // show bootstrap modal when complete loaded
				$('.modal-title').text('Edit task'); // Set title to Bootstrap modal title

			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert('Error get data from ajax');
			}
		});
	}

	function reload_table() {
		location.reload();
	}

	function save() {
		$('#btnSave').text('saving...'); //change button text
		$('#btnSave').attr('disabled', true); //set button disable
		$('.form-group').removeClass('has-error'); // clear error class
		$('.help-block').empty(); // clear error string
		var url;

		if (save_method == 'add') {
			url = "<?php echo site_url('manage/tasks/ajax_add')?>";
		} else {
			url = "<?php echo site_url('manage/tasks/ajax_update')?>";
		}

		// ajax adding data to database
		$.ajax({
			url: url,
			type: "POST",
			data: $('#form').serialize(),
			dataType: "JSON",
			success: function (data) {

				if (data.status) //if success close modal and reload ajax table
				{
					$('#modal_form').modal('hide');
					reload_table();
				}
				else {
					for (var i = 0; i < data.inputerror.length; i++) {

						$('[name="' + data.inputerror[i] + '"]').parent().parent().addClass('has-error'); //select parent twice to select div form-group class and add has-error class
						$('[name="' + data.inputerror[i] + '"]').next().text(data.error_string[i]); //select span help-block class set text error string
					}
				}
				$('#btnSave').text('Save'); //change button text
				$('#btnSave').attr('disabled', false); //set button enable


			},
			error: function (jqXHR, textStatus, errorThrown) {
				if (save_method == 'add') {
					error = "Add new unsuccessful!";
				} else {
					error = "Update unsuccessful!";
				}
				alert(error);
				$('#btnSave').text('save'); //change button text
				$('#btnSave').attr('disabled', false); //set button enable

			}
		});
	}
	function delete_task_confirm(id) {
		$('#form2')[0].reset(); // reset form on modals
		$('.form-group').removeClass('has-error'); // clear error class
		$('[name="idtask"]').val(id);
		$('.help-block').empty(); // clear error string
		$('#modal_form_confirm').modal('show'); // show bootstrap modal
		$('.modal-title').text('Delete task confirm'); // Set Title to Bootstrap modal title
	}
	function delete_task() {
		$('#btnDelete').text('Deleting...'); //change button text
		$('#btnDelete').attr('disabled', true); //set button disable

		// ajax adding data to database
		$.ajax({
			url: "<?php echo site_url('manage/tasks/ajax_delete')?>",
			type: "POST",
			data: $('#form2').serialize(),
			dataType: "JSON",
			success: function (data) {

				if (data.status) //if success close modal and reload ajax table
				{
					$('#modal_form_confirm').modal('hide');
					reload_table();
				}
				else {
					alert("Delete unsuccessful!");
				}
				$('#btnDelete').text('Ok'); //change button text
				$('#btnDelete').attr('disabled', false); //set button enable


			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert("Delete unsuccessful!");
				$('#btnDelete').text('Ok'); //change button text
				$('#btnDelete').attr('disabled', false); //set button enable

			}
		});
	}

</script>

<!-- Bootstrap modal -->
<div class="modal fade" id="modal_form" role="dialog">
	<div class="modal-dialog modal-full">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
						aria-hidden="true">&times;</span></button>
				<h3 class="modal-title">Person Form</h3>
			</div>
			<div class="modal-body form">
				<form action="#" id="form" class="form-horizontal">
					<input type="hidden" value="" name="idtask"/>

					<div class="form-body">
						<div class="panel-body">
                            <div class="form-group">
                                <label class="control-label col-md-3">Title <span
                                        class="text-danger">(*)</span></label>

                                <div class="col-md-6">
                                    <input name="title" placeholder="Title"
                                            class="form-control"
                                            type="text">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class = "form-group">
                                <label class = "control-label col-md-3">Date from<span class = "text-danger">(*)</span></label>

                                <div class = "col-md-6">
                                    <input type = "date" data-date-format = "YYYY-mm-dd" name = "date_from"
                                            class = "form-control daterange-single">
                                    <span class = "help-block"></span>
                                </div>
                            </div>
                            <div class = "form-group">
                                <label class = "control-label col-md-3">Date to<span class = "text-danger">(*)</span></label>

                                <div class = "col-md-6">
                                    <input type = "date" data-date-format = "YYYY-mm-dd" name = "date_to"
                                            class = "form-control daterange-single">
                                    <span class = "help-block"></span>
                                </div>
                            </div>
                            <div class = "form-group">
                                <label class = "control-label col-md-3">Description </label>
                                <div class="col-lg-12">
                                    <textarea name="description"  class = "ckeditor" rows="50" cols="160"></textarea>
                                    <input type="hidden" name="content_hide">
                                </div>
                            </div>
						</div>
					</div>
				</form>
			</div>
            <div class = "modal-footer">
				<div align = "left">
					<span class = "label label-danger">Alert</span> <span class = "text-danger">(*)</span> is necessary, can not be empty.
				</div>
				<button type = "button" id = "btnSave" onclick = "save()" class = "btn btn-primary"><i
						class = "glyphicon glyphicon-floppy-disk"></i> Save
				</button>
				<button type = "button" class = "btn btn-danger" data-dismiss = "modal"><i
						class = "glyphicon glyphicon-remove"></i> Cancel
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!-- Inline form -->
<div class="modal fade" id="modal_form_confirm" title="Inline form">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
						aria-hidden="true">&times;</span></button>
				<h3 class="modal-title">Person Form</h3>
			</div>
			<div class="modal-body form">
				<form action="#" id="form2" class="form-horizontal">
					<input type="hidden" value="" name="idtask"/>

					<div class="form-body">
						<div class="form-group">
							<label class="control-label col-md-9"><h5>Do you sure delete this task?</h5>
							</label>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" id="btnDelete" onclick="delete_task()" class="btn btn-primary"><i
						class="icon-checkmark4"></i> Ok
				</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal"><i
						class="glyphicon glyphicon-remove"></i> Cancel
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
</div>
<!-- /inline form -->
